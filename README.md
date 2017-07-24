This is a quickstart for a Vert.x Scala project. It provides a few examples for doing 
unit-tests.

It comes with vertx-core and vertx-web so you are good to go for a little REST-project.
Take your time and take a look 

# Vert.x-Modules

There is a list of available modules maintained in *project/Dependencies.scala*. Simply add the appropriate
entry to *build.sbt* to use one of the modules.
Here is an example with an added *vertx-hazelcast* dependency for clustering.
```
libraryDependencies ++= Vector (
  Library.vertx_lang_scala,
  Library.vertx_web,
  Library.scalaTest       % "test",
  Library.vertx_codegen,
  Library.vertx_hazelcast
)
```


# Scala console

After launching _sbt_ you can switch to the _scala--console. There we took care that you
get an already initialized Vert.x-instance and the necessary imports to start playing around.
play around in sbt
```
sbt
> console
scala> vertx.deployVerticle(nameForVerticle[HttpVerticle])
scala> vertx.deploymentIDs
```

From here you can freely interact with the Vertx-API inside the sbt-scala-shell.


# Fat-jar

Take a look at the _build.sbt_ and search for the entry _packageOptions_. Enter the fully qualified class name 
of your primary verticle. This will be used as entry point for a generated fat-jar.

To create the runnable fat-jar use:
```
sbt assembly
```

# Dockerize

The project also contains everything you need to create a Docker-container. Most config is done in
*project/Docker.scala*. The only thing you have to do is keep the following line in *build.sbt*:
```
enablePlugins(DockerPlugin)
exposedPorts := Seq(8666)
```
Simply add additional ports to *exposedPorts* as required.

To package your fat-jar inside a Docker-container do this:
```
sbt docker
```
To run the container do:
```
docker run -p 8666:8666 default/vertx-scala-sbt
```
Point your browser to [http://127.0.0.1:8666/hello](http://127.0.0.1:8666/hello) and enjoy :)
