plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "com.github.hyunwoo.picsum.design"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.material)
}
