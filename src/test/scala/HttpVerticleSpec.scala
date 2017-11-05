import org.scalatest.Matchers
import scala.concurrent.Promise
import org.scalatest.concurrent.ScalaFutures
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HttpVerticleSpec extends VerticleTesting[HttpVerticle] with Matchers with ScalaFutures {
  "HttpVerticle" should "bind to 8666 and answer with 'world'" in {
    val promise = Promise[String]

    vertx.createHttpClient()
      .getNow(8666, "127.0.0.1", HttpVerticle.routePath,
        r => {
          r.exceptionHandler(promise.failure)
          r.bodyHandler(b => promise.success(b.toString))
        }
      )

    whenReady(promise.future) {_ shouldBe HttpVerticle.response }
  }
}
