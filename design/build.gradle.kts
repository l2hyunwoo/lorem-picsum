plugins {
    id("com.github.hyunwoo.picsum.feature")
}

android {
    namespace = "com.github.hyunwoo.picsum.design"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
