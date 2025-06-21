// Adds Assembly Plugin for building fat JARs (essential for Spark deployments)
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.1.1")

// Scalafmt for code formatting (professional touch)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

// Dependency updates checker
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")

// Native packager for Docker integration (optional)
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.9.16")
