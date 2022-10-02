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

    implementation(projects.domains.audios.api)

    implementation(projects.features.foregroundPlayer.api)

    implementation(projects.common.designSystem)

    hilt()

    implementation(Dependencies.exoplayerCore)
    implementation(Dependencies.exoplayerUi)
}
