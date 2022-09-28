import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation("com.android.tools.build:gradle:7.4.0-alpha10")
    implementation("com.squareup:javapoet:1.13.0")
}

gradlePlugin {
    plugins {
        create("androidLibraryPlugin") {
            id = "com.betocrod.songrecords.android.library"
            implementationClass = "com.betocrod.songrecords.plugins.LibraryPlugin"
        }
    }
}
