package com.betocrod.features.main.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.betocrod.common.navigation.FeatureDestination

@Composable
fun AppGraph(
    startDestination: String,
    destinations: Set<FeatureDestination>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        destinations.forEach {
            it.registerGraph(
                navGraphBuilder = this,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
