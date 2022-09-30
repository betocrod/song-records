plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.domains.audios.api"
}

dependencies {
    implementation(Dependencies.coroutines)
}
