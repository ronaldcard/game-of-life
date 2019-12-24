import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(module = "jvm") version "1.3.61"
}

group = "com.foolishpuma.kata"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin(module = "stdlib-jdk8"))
    implementation(kotlin(module = "reflect"))
    testImplementation(group = "junit", name = "junit")
    testImplementation(group = "org.assertj", name = "assertj-core", version = "3.+")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
