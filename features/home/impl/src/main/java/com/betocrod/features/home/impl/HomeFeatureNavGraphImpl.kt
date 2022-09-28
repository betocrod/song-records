package com.betocrod.features.home.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.betocrod.features.home.api.HomeFeatureNavGraph
import com.betocrod.features.home.impl.model.HomeState
import com.betocrod.features.home.impl.widgets.previewparameters.getSongList
import javax.inject.Inject

class HomeFeatureNavGraphImpl @Inject constructor() : HomeFeatureNavGraph {

    override fun homeRoute(): String = "home"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(homeRoute()) {
            HomeScaffold(
                homeState = HomeState.SongsLoaded(getSongList()),
                onItemClick = { TODO() }
            )
        }
    }
}
