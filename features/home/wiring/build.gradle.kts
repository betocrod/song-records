plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.songrecords.features.home.wiring"
}

dependencies {
    api(projects.features.home.api)
    implementation(projects.features.home.impl)
}
