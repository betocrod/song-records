package com.betocrod.features.recorder.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betocrod.features.recorder.api.RecorderFeatureNavGraph
import javax.inject.Inject

class RecorderFeatureNavGraphImpl @Inject constructor() : RecorderFeatureNavGraph {

    private val route = "recorder"

    override fun route(songId: Int) = "$route/$songId"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = "$route/{$PARAM_SONG_ID}",
            arguments = listOf(
                navArgument(PARAM_SONG_ID) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            RecorderScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }

    companion object {
        internal const val PARAM_SONG_ID = "songId"
    }
}
