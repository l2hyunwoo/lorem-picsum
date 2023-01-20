import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    id("kotlin")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.javax.inject)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions { jvmTarget = "11" }
