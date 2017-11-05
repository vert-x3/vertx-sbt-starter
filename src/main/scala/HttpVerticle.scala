import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.{Route, Router}
import scala.concurrent.Future

class HttpVerticle extends ScalaVerticle {
  override def startFuture(): Future[Unit] = {
    // Create a router to answer GET-requests to "/hello" with "world"
    val router = Router.router(vertx)

    val route: Route = router   // TODO never used, delete?
      .get("/hello")
      .handler(_.response().end("world"))

    vertx
      .createHttpServer()
      .requestHandler(router.accept(_))
      .listenFuture(8666, "0.0.0.0")
      .map(println)
  }
}
