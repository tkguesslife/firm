name := """firm"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

val appDependencies = Seq(
  javaCore,
  javaWs,
  javaJpa,
  "javax.inject" % "javax.inject" % "1",
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.springframework" % "spring-test" % "4.1.1.RELEASE" % "test",    
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "org.springframework.data" % "spring-data-jpa" % "1.3.2.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "4.0.1.Final",
  "javax.el" % "el-api" % "2.2",
  "org.glassfish.web" % "el-impl" % "2.2",
  "org.json" % "json" % "20140107"
)

lazy val root = project.in(file("."))
  .enablePlugins(PlayJava)
  .settings(
    libraryDependencies ++= appDependencies
  )
