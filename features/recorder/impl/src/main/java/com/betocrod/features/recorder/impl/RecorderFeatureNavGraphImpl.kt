package com.betocrod.features.recorder.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betocrod.features.recorder.api.RecorderFeatureNavGraph
import com.betocrod.features.recorder.impl.widget.previewparameters.getSuccessState
import javax.inject.Inject

class RecorderFeatureNavGraphImpl @Inject constructor() : RecorderFeatureNavGraph {

    private val route = "recorder"

    private val paramSongId = "songId"

    override fun route(songId: String) = "$route/$songId"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = "$route/{$paramSongId}",
            arguments = listOf(
                navArgument(paramSongId) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            RecordScreenScaffold(
                state = getSuccessState(),
                onRecordClick = { /*TODO*/ },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
