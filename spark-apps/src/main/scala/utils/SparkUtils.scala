package utils

import org.apache.spark.sql.types._
import org.apache.spark.sql.{SparkSession, DataFrame}

object SparkUtils {
  def initSpark(): SparkSession = {
    SparkSession.builder()
      .appName("FraudDetectionEngine")
      .config("spark.sql.warehouse.dir", "/user/hive/warehouse")
      .enableHiveSupport()
      .getOrCreate()
  }

  val transactionSchema = StructType(Seq(
    StructField("transaction_id", StringType),
    StructField("user_id", StringType),
    StructField("amount", DoubleType),
    StructField("merchant", StringType),
    StructField("location", StringType)
  ))
}
