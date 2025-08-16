import org.slf4j.LoggerFactory

val javaVersion: String by project

private val log by lazy { LoggerFactory.getLogger("me.riddle.fintech.canary.build") }

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
        vendor.set(JvmVendorSpec.ADOPTIUM)
        log.info("\t|=> Riddle me that Java Toolchain SET to    -> $javaVersion : ${JvmVendorSpec.ADOPTIUM}.")
    }
}


application {
    log.info("\t|=> Riddle me that Java Application SET to    -> ${project.name} : ${project.version}.")
    mainClass = "me.riddle.fintech.AppKt"
}

dependencies {
    implementation(gradleApi())
    implementation(platform(kotlin("bom")))

    api(libs.slf4j.api)
    implementation(libs.logback.classic)

    // Add these Ktor dependencies:
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Add Kotlin coroutines and serialization:
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // Your existing test dependencies stay as they are:
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Zip>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}

tasks.withType<Tar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}