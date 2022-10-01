plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.features.song.wiring"
}

dependencies {
    api(projects.features.foregroundPlayer.api)
    implementation(projects.features.foregroundPlayer.impl)

    hilt()
}
