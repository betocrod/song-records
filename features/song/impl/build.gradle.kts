@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
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

    implementation(projects.common.designSystem)
    implementation(projects.common.navigation)

    implementation(projects.domains.audios.api)

    implementation(projects.features.song.api)
    implementation(projects.features.recorder.api)
    implementation(projects.features.foregroundPlayer.api)

    composeLibraries()
    hilt()

    implementation(Dependencies.exoplayerCore)
    implementation(Dependencies.hiltCompose)
}
