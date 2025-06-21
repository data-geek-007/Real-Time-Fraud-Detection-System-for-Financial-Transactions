name := "fraud-detection"
version := "1.0"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.0",
  "org.apache.spark" %% "spark-sql" % "3.3.0",
  "org.apache.spark" %% "spark-mllib" % "3.3.0",
  "org.apache.spark" %% "spark-streaming" % "3.3.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.3.0",
  "org.apache.kafka" % "kafka-clients" % "3.2.0",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test
)
