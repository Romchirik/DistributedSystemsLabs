plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    //logging interface
    implementation("org.slf4j:slf4j-api:2.0.6")

    //logback
    implementation("ch.qos.logback:logback-core:1.3.5")
    implementation("ch.qos.logback:logback-classic:1.3.5")

    //archivers
    implementation("org.apache.commons:commons-compress:1.22")

    //cli tools
    implementation("info.picocli:picocli:4.7.1")
    annotationProcessor("info.picocli:picocli-codegen:4.7.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("ru.nsu.titov.app.BZReaderApplication")
}