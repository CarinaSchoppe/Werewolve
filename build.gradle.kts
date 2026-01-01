plugins {
    kotlin("jvm") version "2.3.0" apply false
    id("com.github.ben-manes.versions") version "0.53.0" apply false
}

group = "me.carinaschoppe"
version = "1.0-SNAPSHOT"
description = "A copy of the famous werewolf game with a chatgpt instance in it"


subprojects {
    apply(plugin = "com.github.ben-manes.versions")

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>().configureEach {
        rejectVersionIf {
            val unstable = listOf("alpha", "beta", "rc", "m", "preview", "ea", "snapshot", "dev")
            unstable.any { candidate.version.contains(it, ignoreCase = true) }
        }
    }
}


allprojects {

    repositories {
        mavenCentral()
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }
}
