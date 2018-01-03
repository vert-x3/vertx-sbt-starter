import io.vertx.scala.core.eventbus.Message
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.JUnitRunner
import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class BusVerticleSpec extends VerticleTesting[BusVerticle] with Matchers with ScalaFutures {
  import BusVerticle._

  "BusVerticle" should "reply to a message" in {
    val future: Future[Message[String]] =
      vertx
        .eventBus
        .sendFuture(testAddress, testMessage)

    whenReady(future) { _.body shouldBe replyMessage }
  }
}
