import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future

object BusVerticle {
  val replyMessage = "Hello World!"
}

class BusVerticle extends ScalaVerticle {
  override def startFuture(): Future[Unit] = {
    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(_.reply(BusVerticle.replyMessage))
      .completionFuture()
  }
}
