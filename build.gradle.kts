buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.junit5.classpath)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
}
