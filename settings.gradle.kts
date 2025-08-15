pluginManagement {

    val versionOfToolchainsFoojayResolver: String by extra
    val verKotlin: String by extra


    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version versionOfToolchainsFoojayResolver
        kotlin("jvm") version verKotlin
    }
}

rootProject.name = "fintech-future-canary"

include("app")
