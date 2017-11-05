import io.vertx.scala.core.eventbus.Message
import scala.concurrent.Future
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class BusVerticleSpec extends VerticleTesting[BusVerticle] with Matchers with ScalaFutures {
  "BusVerticle" should "reply to a message" in {
    val future: Future[Message[String]] =
      vertx
        .eventBus
        .sendFuture("testAddress", "msg")

    whenReady(future) { _.body shouldBe BusVerticle.replyMessage }
  }
}
