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

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    api(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

// A known Gradle 9 quirk - they also use the `stdlib`
tasks.withType<Zip>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
tasks.withType<Tar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}