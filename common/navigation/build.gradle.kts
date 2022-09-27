@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.common.navigation"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeNavigation)
}
