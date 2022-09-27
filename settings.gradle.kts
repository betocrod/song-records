@file:Suppress("UnstableApiUsage")

include(":navigation")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "song-records"

fun includeFeatures(vararg featureName: String) {
    featureName.forEach {
        include(":features:$it:api", ":features:$it:impl", ":features:$it:wiring")
    }
}

fun includeCommons(vararg moduleName: String) {
    moduleName.forEach {
        include(":common:$it")
    }
}

include(":app")
includeCommons("designSystem", "navigation")
includeFeatures("home", "song", "recorder")
