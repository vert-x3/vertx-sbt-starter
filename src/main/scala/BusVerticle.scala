import io.vertx.lang.scala.ScalaVerticle
import scala.concurrent.Future

object BusVerticle {
  val testAddress = "testAddress"
  val testMessage = "msg"
  val replyMessage = "Hello World!"
}

class BusVerticle extends ScalaVerticle {
  override def startFuture(): Future[Unit] = {
    vertx
      .eventBus
      .consumer[String](BusVerticle.testAddress)
      .handler(_.reply(BusVerticle.replyMessage))
      .completionFuture
  }
}
