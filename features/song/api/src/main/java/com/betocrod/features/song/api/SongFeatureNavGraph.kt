package com.betocrod.features.song.api

import com.betocrod.common.navigation.FeatureNavGraph

interface SongFeatureNavGraph : FeatureNavGraph {

    fun route(songId: Int): String
}
