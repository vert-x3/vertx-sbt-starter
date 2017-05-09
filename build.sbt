import sbt.Package._
import sbt._
import Docker.autoImport.exposedPorts

scalaVersion := "2.12.1"

enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)

libraryDependencies ++= Vector (
  Library.vertxLangScala,
  Library.vertxCodegen,
  Library.vertxWeb,
  Library.scalaTest       % "test"
)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:io.vertx.scala.sbt.HttpVerticle"))

