plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.data"
    compileSdk = 33

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)
}