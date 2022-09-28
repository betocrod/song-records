package com.betocrod.features.home.api

import com.betocrod.common.navigation.FeatureNavGraph

interface HomeFeatureNavGraph: FeatureNavGraph {

    fun homeRoute(): String
}
