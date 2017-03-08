package io.vertx.scala.sbt

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class BusVerticle extends ScalaVerticle {

  override def start(): Future[Unit] = {
    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(_.reply("Hello World!"))
      .completionFuture()
  }
}
