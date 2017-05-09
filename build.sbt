import sbt.Package._
import sbt._
import Docker.autoImport.exposedPorts

scalaVersion := "2.12.1"

enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)

libraryDependencies ++= Vector (
  Library.vertxLangScala,
  Library.vertxWeb,
  Library.scalaTest       % "test",
  // Uncomment for clustering
  // Library.vertxHazelcast,

  //required to get rid of some warnings emitted by the scala-compile
  Library.vertxCodegen
)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:HttpVerticle"))

