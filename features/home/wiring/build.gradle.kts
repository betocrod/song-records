plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.features.home.wiring"
}

dependencies {
    implementation(projects.common.navigation)

    api(projects.features.home.api)
    implementation(projects.features.home.impl)

    hilt()
}
