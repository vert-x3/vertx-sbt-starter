import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.{Route, Router}
import scala.concurrent.Future

object HttpVerticle {
  val routePath = "/hello"
  val response = "world"
}

class HttpVerticle extends ScalaVerticle {
  import HttpVerticle._

  override def startFuture(): Future[Unit] = {
    // Create a router to answer GET-requests to "/hello" with "world"
    val router = Router.router(vertx)

    val route: Route = router       // unclear how this value is picked up
      .get(routePath)
      .handler(_.response.end(response))

    vertx
      .createHttpServer()
      .requestHandler(router.accept(_))
      .listenFuture(8666, "0.0.0.0")    // listen in promiscuous mode
      .map { httpServer =>
        println(s"""httpServer.isMetricsEnabled: ${ httpServer.isMetricsEnabled }
                    |httpServer connected on port: ${ httpServer.actualPort }
                    |""".stripMargin)
      }
  }
}
