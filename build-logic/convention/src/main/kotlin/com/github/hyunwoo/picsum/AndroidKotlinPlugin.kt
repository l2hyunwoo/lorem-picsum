package com.github.hyunwoo.picsum

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("kotlin-android")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        extensions.getByType<BaseExtension>().apply {
            setCompileSdkVersion(33)

            defaultConfig {
                minSdk = 26
                targetSdk = 33
            }

            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            (this as ExtensionAware).configure<KotlinJvmOptions> {
                jvmTarget = "11"
            }
        }

        dependencies {
            "coreLibraryDesugaring"(libs.findLibrary("desugarJdkLibs").get())
            "implementation"(libs.findLibrary("kotlin.stdlib").get())
            "implementation"(libs.findLibrary("kotlin.coroutines.android").get())
            "implementation"(libs.findLibrary("kotlin.datetime").get())
        }
    }
}
