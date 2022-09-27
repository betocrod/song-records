@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.features.song.impl"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {

    implementation(projects.designSystem)
    implementation(projects.features.song.api)

    composeLibraries()
    implementation(Dependencies.composeCoil)
}
