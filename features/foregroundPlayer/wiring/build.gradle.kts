plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.features.foregroundplayer.wiring"
}

dependencies {
    api(projects.features.foregroundPlayer.api)
    implementation(projects.features.foregroundPlayer.impl)

    implementation(Dependencies.exoplayerCore)

    hilt()
}
