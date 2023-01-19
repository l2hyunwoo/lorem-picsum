plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.profile"
    compileSdk = 33

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:design"))
    implementation(project(":core:common"))
}
