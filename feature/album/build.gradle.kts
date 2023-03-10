plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.album"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:design"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:image"))
    implementation(project(":domain"))
    implementation(libs.fragment)
    implementation(libs.paging.runtime)
}