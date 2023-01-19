plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.common"
    compileSdk = 33

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
