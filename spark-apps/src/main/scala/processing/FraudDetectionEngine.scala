package processing

import ingestion.KafkaStreamReader
import ml.FraudModel
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import utils.SparkUtils

object FraudDetectionEngine {
  def main(args: Array[String]): Unit = {
    val spark = SparkUtils.initSpark()
    import spark.implicits._

    // 1. Stream from Kafka
    val rawStream = KafkaStreamReader.readFromKafka(spark, "transactions")
      .selectExpr("CAST(value AS STRING)")

    // 2. Parse JSON and enrich
    val parsedData = rawStream.transform { df =>
      df.withColumn("parsed", from_json($"value", SparkUtils.transactionSchema))
        .select("parsed.*")
        .withColumn("transaction_time", current_timestamp())
        .withColumn("is_high_value", $"amount" > 5000)
    }

    // 3. Apply ML model
    val scoredData = FraudModel.scoreTransactions(parsedData)

    // 4. Write to Hive
    scoredData.writeStream
      .format("parquet")
      .option("path", "/data/fraud_output")
      .option("checkpointLocation", "/checkpoints/fraud")
      .start()
      .awaitTermination()
  }
}
