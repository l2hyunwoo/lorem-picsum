plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "com.github.hyunwoo.picsum.design"
    compileSdk = 33

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.material)
}
