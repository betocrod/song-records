@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.betocrod.songrecords"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(projects.common.designSystem)
    implementation(projects.common.navigation)

    implementation(projects.domains.audios.wiring)

    implementation(projects.features.main.wiring)
    implementation(projects.features.home.wiring)
    implementation(projects.features.song.wiring)
    implementation(projects.features.recorder.wiring)
    implementation(projects.features.foregroundPlayer.wiring)

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.exoplayerCore)

    composeLibraries()
    hilt()

    unitTestLibraries()
    androidTestImplementation(Dependencies.androidTestLibraries)
}
