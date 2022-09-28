package com.betocrod.features.song.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betocrod.features.song.api.SongFeatureNavGraph
import com.betocrod.features.song.impl.widgets.previewparameters.getSongData
import javax.inject.Inject

class SongFeatureNavGraphImpl @Inject constructor(): SongFeatureNavGraph {

    private val route = "song"

    private val paramSongId = "songId"

    override fun route(songId: String) = "$route/$songId"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = route,
            arguments = listOf(
                navArgument(paramSongId) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            SongScaffold(songState = getSongData())
        }
    }
}
