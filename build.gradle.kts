group = "me.carinaschoppe"
version = "1.0-SNAPSHOT"
description = "A copy of the famous werewolf game with a chatgpt instance in it"
plugins {
    id("com.github.ben-manes.versions") version "0.53.0"
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
