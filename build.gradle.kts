plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
  mavenCentral()
}

dependencies {
  val vertxVersion = "3.7.0"
  implementation("io.vertx:vertx-web:${vertxVersion}")
}

application {
  mainClassName = "io.vertx.howtos.openj9.Main"
}

tasks.wrapper {
  gradleVersion = "5.4.1"
}
