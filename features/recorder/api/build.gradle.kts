plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.features.recorder.api"
}

dependencies {
    implementation(projects.common.navigation)
}
