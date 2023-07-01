plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    application
}

repositories {
    mavenCentral()

    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.google.guava:guava:32.1.0-jre")

    testImplementation("org.jetbrains.kotlin:kotlin-test")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    
    implementation("dev.kord:kord-core:395-lazy-intents-SNAPSHOT")

    implementation("net.mamoe.yamlkt:yamlkt:0.13.0")

    implementation("com.beust:klaxon:5.6")
}

application {
    mainClass.set("core.BotKt")
}
