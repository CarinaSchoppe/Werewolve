plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Werewolf"

include("core", "desktop", "minecraft")

project(":minecraft").buildFileName = "build.gradle.kts"
project(":core").buildFileName = "build.gradle.kts"
project(":desktop").buildFileName = "build.gradle.kts"
