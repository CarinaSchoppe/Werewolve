plugins {
    kotlin("jvm")
    application
    kotlin("plugin.serialization") version "2.2.10"
    id("org.flywaydb.flyway") version "11.20.0"
}

dependencies {
    implementation("com.openai:openai-java:4.13.0")


    implementation("ai.koog:koog-agents:0.6.0")

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // Ktor (Version z.B. 3.3.3 laut Maven Central)
    implementation("io.ktor:ktor-server-netty:3.3.3")
    implementation("io.ktor:ktor-server-websockets:3.3.3")
    implementation("io.ktor:ktor-server-content-negotiation:3.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.3")

    // Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation("org.jetbrains.exposed:exposed-core:1.0.0-rc-4")
    implementation("org.jetbrains.exposed:exposed-jdbc:1.0.0-rc-4")
    implementation("org.jetbrains.exposed:exposed-dao:1.0.0-rc-4")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:1.0.0-rc-4")
    implementation("org.xerial:sqlite-jdbc:3.51.1.0")

    implementation("io.github.oshai:kotlin-logging-jvm:7.0.14")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.23")
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.kotest:kotest-runner-junit5:6.0.7")
    testImplementation("io.kotest:kotest-assertions-core:6.0.7")
    testImplementation("io.mockk:mockk:1.14.7")
}

kotlin { jvmToolchain(21) }
tasks.test { useJUnitPlatform() }


application {
    mainClass.set("me.carinaschoppe.werewolf.MainKt")
}
