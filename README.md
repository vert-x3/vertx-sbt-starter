This is a quickstart for a Vert.x Scala project. 
It provides a few sample unit tests.

The project depends on `vertx-core` and `vertx-web` so the project can provide REST functionality.
Take your time and take a look around.

# Vert.x Modules

There is a list of available modules maintained in `project/Dependencies.scala`. 
Simply add the appropriate entry to `build.sbt` to use one of the modules.
Here is an example with an added `vertx-hazelcast` dependency for clustering.
```
libraryDependencies ++= Vector (
  Library.vertx_lang_scala,
  Library.vertx_web,
  Library.vertx_codegen,
  Library.vertx_hazelcast,
  Library.scalaTest % Test
)
```


# Scala Console

In the following code, we launch `sbt` and enter the `sbt` console. 
Then we obtain an initialized Vert.x instance and the necessary imports to start playing around.
```
$ sbt console
scala> vertx.deployVerticle(nameForVerticle[HttpVerticle])
scala> vertx.deploymentIDs
```

Now you can use the `sbt console` to discover all sorts of things about the Vertx API.


# Fat Jars

Take a look at `build.sbt` and search for the entry `packageOptions`. 
Enter the fully qualified class name of your primary verticle. 
This will be used as entry point for a generated fat jar.

To create the runnable fat jar, type:
```
$ sbt assembly
```

# Dockerize

The project also contains everything you need to create a Docker container.
Most configuration is done in `project/Docker.scala`. 
The only thing you have to do is keep the following line in `build.sbt`:
```
enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)
```
Simply add additional ports to `exposedPorts` as required.

To package your fat jar inside a Docker container type the following at a shell prompt:
```
$ sbt docker
```

To run the container, type:
```
docker run -p 8666:8666 default/vertx-sbt-starter
```
Point your browser to [http://127.0.0.1:8666/hello](http://127.0.0.1:8666/hello) and enjoy :)
