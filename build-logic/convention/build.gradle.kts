plugins {
    `kotlin-dsl`
}

group = "com.github,hyunwoo.picsum.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
}
