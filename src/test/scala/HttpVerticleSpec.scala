import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.JUnitRunner
import scala.concurrent.Promise

@RunWith(classOf[JUnitRunner])
class HttpVerticleSpec extends VerticleTesting[HttpVerticle] with Matchers with ScalaFutures {
  import HttpVerticle._

  "HttpVerticle" should s"bind to 8666 and answer with '$response'" in {
    val promise = Promise[String]

    vertx.createHttpClient
      .getNow(8666, "127.0.0.1", routePath,
        httpClientResponse => {
          httpClientResponse.exceptionHandler(promise.failure)                       // complete promise with failure
          httpClientResponse.bodyHandler(buffer => promise.success(buffer.toString)) // complete promise with success
        }
      )

    whenReady(promise.future) {_ shouldBe response }
  }
}
