name := "resource-server"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream"          % "2.6.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
)
