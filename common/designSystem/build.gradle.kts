@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.betocrod.songrecords.android.library")
}

android {
    namespace = "com.betocrod.designsystem"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    composeLibraries()
    implementation(Dependencies.composeCoil)
    implementation(Dependencies.appCompat)
}
