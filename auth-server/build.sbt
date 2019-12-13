name := "auth-server"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig"           % "0.12.1",
  "com.pauldijou"         %% "jwt-spray-json"       % "4.2.0",
  "com.typesafe.akka"     %% "akka-stream"          % "2.6.1",
  "com.typesafe.akka"     %% "akka-http-spray-json" % "10.1.11",
)
