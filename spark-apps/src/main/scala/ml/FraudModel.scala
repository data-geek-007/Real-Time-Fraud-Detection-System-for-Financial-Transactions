package ml

import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.{DataFrame, SparkSession}

object FraudModel {
  private var model: PipelineModel = _

  def loadModel(spark: SparkSession, path: String): Unit = {
    model = PipelineModel.load(path)
  }

  def scoreTransactions(data: DataFrame): DataFrame = {
    require(model != null, "Model not loaded! Call loadModel() first")
    model.transform(data)
      .withColumn("is_fraud", $"prediction" > 0.5)
      .drop("features", "rawPrediction", "probability")
  }
}
