name := """smart-campus"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "webjars" at "http://webjars.github.com/m2"
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0-2",
  "org.webjars" % "bootstrap" % "3.0.0" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "1.8.3",
  "mysql" % "mysql-connector-java" % "5.1.34",
  "org.projectlombok" % "lombok" % "1.16.8",
  javaJdbc,
  cache,
  javaWs
)

routesGenerator := InjectedRoutesGenerator

fork in run := true
