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

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.github.hyunwoo.picsum.application"
            implementationClass = "com.github.hyunwoo.picsum.AndroidApplicationPlugin"
        }
        create("android-feature") {
            id = "com.github.hyunwoo.picsum.feature"
            implementationClass = "com.github.hyunwoo.picsum.AndroidFeaturePlugin"
        }
        create("android-kotlin") {
            id = "com.github.hyunwoo.picsum.kotlin"
            implementationClass = "com.github.hyunwoo.picsum.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.github.hyunwoo.picsum.hilt"
            implementationClass = "com.github.hyunwoo.picsum.AndroidHiltPlugin"
        }
        create("kotlin-serialization") {
            id = "com.github.hyunwoo.picsum.serialization"
            implementationClass = "com.github.hyunwoo.picsum.KotlinSerializationPlugin"
        }
    }
}
