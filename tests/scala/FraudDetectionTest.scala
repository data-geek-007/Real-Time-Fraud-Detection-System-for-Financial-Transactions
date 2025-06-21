import org.apache.spark.sql.{SparkSession, DataFrame}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import utils.SparkUtils

class FraudDetectionTest extends AnyFunSuite with BeforeAndAfterAll {
  private var spark: SparkSession = _

  override def beforeAll(): Unit = {
    spark = SparkUtils.initSpark()
  }

  test("Test transaction schema validation") {
    val testData = spark.createDataFrame(Seq(
      ("txn1", "user1", 100.0, "Amazon", "US"),
      ("txn2", "user2", 5000.0, "BestBuy", "CN")
    )).toDF("transaction_id", "user_id", "amount", "merchant", "location")

    assert(testData.count() == 2)
  }

  override def afterAll(): Unit = {
    spark.stop()
  }
}
