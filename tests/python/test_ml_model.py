import unittest
from pyspark.sql import SparkSession
from pyspark.ml import PipelineModel

class TestFraudModel(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.spark = SparkSession.builder.master("local[2]").getOrCreate()

    def test_model_loading(self):
        model = PipelineModel.load("/models/fraud_model")
        self.assertIsNotNone(model.stages)

    @classmethod
    def tearDownClass(cls):
        cls.spark.stop()
