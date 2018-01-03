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
  protected val vertx: Vertx = Vertx.vertx
  implicit protected val vertxExecutionContext: VertxExecutionContext = VertxExecutionContext(vertx.getOrCreateContext)

  private var deploymentId: String = ""
  private val duration: Duration = Duration.Inf // good value for testing, bad value for production

  before {
    val aTypeName: String = implicitly[TypeTag[A]].tpe.typeSymbol.fullName  // "BusVerticle"
    deploymentId = Await.result(
      vertx
        .deployVerticleFuture(s"scala:$aTypeName", DeploymentOptions().setConfig(Json.emptyObj))
        .andThen {
          case Success(id) => id   // typical value: 2115d175-e724-4f2a-aaa6-2dadbf733370
          case Failure(throwable) => throw throwable
        },
      duration
    )
  }

  after {
    Await.ready(
      vertx
        .undeployFuture(deploymentId)
        .andThen {
          case Success(unit) => unit
          case Failure(throwable) => throw throwable
        },
      duration
    )
  }
}
