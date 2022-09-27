buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

plugins {
    id("com.android.application")  apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false
}
