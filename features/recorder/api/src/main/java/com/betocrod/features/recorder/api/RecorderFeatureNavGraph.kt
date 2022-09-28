package com.betocrod.features.recorder.api

import com.betocrod.common.navigation.FeatureNavGraph

interface RecorderFeatureNavGraph: FeatureNavGraph {

    fun route(songId: String): String
}
