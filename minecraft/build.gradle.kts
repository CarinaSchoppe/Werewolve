plugins {
    id("java")
    kotlin("jvm")
    id("xyz.jpenilla.run-paper") version "3.0.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(project(":core"))
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

kotlin { jvmToolchain(21) }

tasks {
    runServer {
        minecraftVersion("1.21.8")
    }
}
