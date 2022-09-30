package com.betocrod.features.home.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.betocrod.features.home.api.HomeFeatureNavGraph
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
            HomeScreen(
                onItemClick = { navController.navigateToSongScreen(it.id) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    private fun NavHostController.navigateToSongScreen(songId: Int) {
        val route = songFeatureNavGraph.route(songId)
        navigate(route)
    }
}
