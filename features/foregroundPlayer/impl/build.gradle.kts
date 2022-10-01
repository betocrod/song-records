@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.features.foregroundplayer.impl"
}

dependencies {

    implementation(projects.features.foregroundPlayer.api)

    hilt()

    implementation(Dependencies.exoplayer)
}
