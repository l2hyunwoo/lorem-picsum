import java.util.*

plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.data"
    compileSdk = 33

    val properties = Properties().apply {
        load(file("../../local.properties").inputStream())
    }

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "API_URL", properties["apiUrl"].toString())
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)
}