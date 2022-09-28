package com.betocrod.features.home.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.betocrod.features.home.api.HomeFeatureNavGraph
import com.betocrod.features.home.impl.model.HomeState
import com.betocrod.features.home.impl.widgets.previewparameters.getSongList
import com.betocrod.features.song.api.SongFeatureNavGraph
import javax.inject.Inject

class HomeFeatureNavGraphImpl @Inject constructor(
    private val songFeatureNavGraph: SongFeatureNavGraph
) : HomeFeatureNavGraph {

    override fun homeRoute(): String = "home"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(homeRoute()) {
            HomeScaffold(
                homeState = HomeState.SongsLoaded(getSongList()),
                onItemClick = { navController.navigateToSongScreen() }
            )
        }
    }

    private fun NavHostController.navigateToSongScreen() {
        val route = songFeatureNavGraph.route("songId")
        navigate(route)
    }
}
