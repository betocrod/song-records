package com.betocrod.features.main.impl

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.betocrod.common.navigation.FeatureDestination
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.main.api.AppGraphRenderer
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppGraphRendererImpl @Inject constructor(
    private val activity: Activity,
    private val destinations: Set<FeatureDestination>
): AppGraphRenderer {

    override fun render(startDestination: String) {
        (activity as ComponentActivity).setContent {
            SongRecordsTheme {
                AppGraph(startDestination = startDestination, destinations = destinations)
            }
        }
    }
}
