pluginManagement {

    val versionOfToolchainsFoojayResolver: String by extra
    val verKotlin: String by extra
    val verKotlinSerialization: String by extra


    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version versionOfToolchainsFoojayResolver
        // Note; plugins are not in TOML intentionally
        kotlin("jvm") version verKotlin
        kotlin("plugin.serialization") version verKotlinSerialization
    }
}

rootProject.name = "fintech-future-canary"

include("app")
