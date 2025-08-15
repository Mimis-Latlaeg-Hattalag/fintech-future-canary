import org.slf4j.LoggerFactory

private val log by lazy { LoggerFactory.getLogger("me.riddle.fintech.canary.build") }

plugins {
    kotlin("jvm") apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

log.info("\t|=> Future FinTech Canary (${project.name} : ${project.version}).")