package com.betocrod.features.home.wiring

import com.betocrod.common.navigation.FeatureNavGraph
import com.betocrod.features.home.api.HomeFeatureNavGraph
import com.betocrod.features.home.impl.HomeFeatureNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeWiringModule {

    @IntoSet
    @Binds
    abstract fun bindNavGraph(navGraphImpl: HomeFeatureNavGraphImpl): FeatureNavGraph

    @Binds
    abstract fun bindFeatureNavGraph(navGraphImpl: HomeFeatureNavGraphImpl): HomeFeatureNavGraph
}
