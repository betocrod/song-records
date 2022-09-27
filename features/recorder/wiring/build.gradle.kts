plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.songrecords.features.recorder.wiring"
}

dependencies {
    api(projects.features.recorder.api)
    implementation(projects.features.recorder.impl)
}
