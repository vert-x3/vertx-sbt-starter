import io.vertx.lang.scala.json.Json
import io.vertx.lang.scala.{ScalaVerticle, VertxExecutionContext}
import io.vertx.scala.core.{DeploymentOptions, Vertx}
import org.scalatest.{AsyncFlatSpec, BeforeAndAfter}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

abstract class VerticleTesting[A <: ScalaVerticle: TypeTag] extends AsyncFlatSpec with BeforeAndAfter {
  val vertx: Vertx = Vertx.vertx
  implicit val vertxExecutionContext: VertxExecutionContext = VertxExecutionContext(vertx.getOrCreateContext)

  private var deploymentId = ""

  before {
    val aTypeName: String = implicitly[TypeTag[A]].tpe.typeSymbol.fullName
    deploymentId = Await.result(
      vertx
        .deployVerticleFuture(s"scala:$aTypeName", DeploymentOptions().setConfig(Json.emptyObj))
        .andThen {
          case Success(d) => d
          case Failure(throwable) => throw new RuntimeException(throwable)
        },
      10000 millis
    )
  }

  after {
    Await.result(
      vertx.undeployFuture(deploymentId)
        .andThen {
          case Success(d) => d
          case Failure(throwable) => throw new RuntimeException(throwable)
        },
      10000 millis
    )
  }
}
