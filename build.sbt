seq(webSettings :_*)

name := "Websocket Draggable"

version := "1.0.0"

scalaVersion := "2.10.0"

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container" artifacts (Artifact("javax.servlet", "jar", "jar")
  )
)

libraryDependencies ++= Seq(
  "javax.servlet" % "servlet-api" % "2.5" % "provided"
)

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.5.v20120716" % "container" artifacts (Artifact("jetty-webapp", "jar", "jar")),
  "org.eclipse.jetty" % "jetty-websocket" % "8.1.5.v20120716" artifacts (Artifact("jetty-websocket", "jar", "jar"))
)

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.6"


