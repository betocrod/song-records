package com.betocrod.songrecords

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.betocrod.features.home.api.HomeFeatureNavGraph
import com.betocrod.features.main.api.AppGraphRenderer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appGraphRenderer: AppGraphRenderer

    @Inject
    lateinit var homeFeatureNavGraph: HomeFeatureNavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val route = homeFeatureNavGraph.homeRoute()
        appGraphRenderer.render(route)
    }
}
