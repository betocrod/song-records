package com.betocrod.songrecords.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class LibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.configureAndroidBlock()
    }
}

private fun Project.configureAndroidBlock() {
    extensions.getByType<LibraryExtension>().run {
        compileSdk = AppConfig.compileSdk
        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk

            testInstrumentationRunner = AppConfig.androidTestInstrumentation
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        packagingOptions {
            resources.excludes.add("META-INF/*")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        testOptions {
            unitTests.apply {
                isReturnDefaultValues = true
            }
            animationsDisabled = true
        }
    }
}
