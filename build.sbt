name := """enterprise"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
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
  "org.hibernate" % "hibernate-entitymanager" % "4.0.1.Final"
)
