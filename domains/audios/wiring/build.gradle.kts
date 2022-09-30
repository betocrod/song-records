plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.betocrod.songrecords.domains.audios.wiring"
}

dependencies {

    api(projects.domains.audios.api)
    implementation(projects.domains.audios.impl)

    hilt()
}
