@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.features.home.impl"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {

    implementation(projects.common.designSystem)
    implementation(projects.features.home.api)

    composeLibraries()
    hilt()

    implementation(Dependencies.composeCoil)
}
