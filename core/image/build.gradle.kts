plugins {
    id("com.github.hyunwoo.picsum.feature")
    id("de.mannodermaus.android-junit5")
}

android {
    namespace = "com.github.hyunwoo.picsum.image"
    compileSdk = 33

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.truth)
    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.params)
    testRuntimeOnly(libs.junit5.engine)
}