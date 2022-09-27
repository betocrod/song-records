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
    api(projects.features.song.api)
    implementation(projects.features.song.impl)

    hilt()
}
