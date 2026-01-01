plugins {
    kotlin("jvm") version "2.3.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.10.2")
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

kotlin { jvmToolchain(25) }

application {
    // mainClass.set("me.carinaschoppe.werewolf.desktop.DesktopAppKt")
}
