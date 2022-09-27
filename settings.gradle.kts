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

include(":app")
include(":designSystem")
includeFeatures("home", "song")
