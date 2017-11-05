import sbt.Package._
import Docker.autoImport.exposedPorts

enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)

libraryDependencies ++= Vector (
  Library.vertx_lang_scala,
  Library.vertx_web,
  Library.scalaTest,
  Library.junit,

  // Library.vertx_hazelcast,   // Uncomment for clustering

  // Required to get rid of some warnings emitted by the scala-compile
  Library.vertx_codegen
)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:HttpVerticle")
)
