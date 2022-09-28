plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.features.recorder.wiring"
}

dependencies {
    
    implementation(projects.common.navigation)

    api(projects.features.recorder.api)
    implementation(projects.features.recorder.impl)

    hilt()
}
