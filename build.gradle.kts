plugins {
    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.lucene:lucene-core:9.6.0")
    implementation("org.apache.lucene:lucene-queryparser:9.6.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}