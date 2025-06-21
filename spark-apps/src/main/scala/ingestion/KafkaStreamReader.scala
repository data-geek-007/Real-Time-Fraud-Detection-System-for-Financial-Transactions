package ingestion

import org.apache.spark.sql.{DataFrame, SparkSession}

object KafkaStreamReader {
  def readFromKafka(spark: SparkSession, topic: String): DataFrame = {
    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", topic)
      .option("startingOffsets", "latest")
      .load()
  }
}
