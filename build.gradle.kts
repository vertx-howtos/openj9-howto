plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
  mavenCentral()
}

dependencies {
  val vertxVersion = "5.0.0.CR2"
  implementation("io.vertx:vertx-web:${vertxVersion}")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}


application {
  mainClass = "io.vertx.howtos.openj9.Main"
}
