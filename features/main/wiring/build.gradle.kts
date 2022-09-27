plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.features.main.wiring"
}

dependencies {
    api(projects.features.main.api)
    implementation(projects.features.main.impl)

    hilt()
}
