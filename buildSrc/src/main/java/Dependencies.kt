import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    // App dependencies
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    // Android
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    // Compose
    const val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeUITooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUITestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    // DI
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltCompose =  "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Coil
    const val composeCoil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Exoplayer
    const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
    const val exoplayerUi = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"

    // Material
    const val material3 = "androidx.compose.material3:material3:${Versions.material3}"

    // Test
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    // Android test
    private const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    private const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    // Coroutines
    const val coroutines =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidJUnit)
        add(espresso)
        add(composeJUnit)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

fun DependencyHandler.unitTestLibraries() {
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
}

fun DependencyHandler.composeLibraries(withPreview: Boolean = true) {
    implementation(Dependencies.composeUI)
    implementation(Dependencies.material3)
    implementation(Dependencies.composeNavigation)
    if (withPreview) implementation(Dependencies.composePreview)
    debugImplementation(Dependencies.composeUITooling)
    debugImplementation(Dependencies.composeUITestManifest)
}

fun DependencyHandler.hilt() {
    add("implementation", Dependencies.hilt)
    add("kapt", Dependencies.hiltCompiler)
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}
