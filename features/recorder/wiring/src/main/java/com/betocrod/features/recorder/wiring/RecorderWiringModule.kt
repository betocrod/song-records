package com.betocrod.features.recorder.wiring

import com.betocrod.common.navigation.FeatureNavGraph
import com.betocrod.features.recorder.api.RecorderFeatureNavGraph
import com.betocrod.features.recorder.impl.RecorderFeatureNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class RecorderWiringModule {

    @Binds
    @IntoSet
    abstract fun bindFeatureNavGraph(navGraph: RecorderFeatureNavGraphImpl): FeatureNavGraph

    @Binds
    abstract fun bindRecorderNavGraph(navGraph: RecorderFeatureNavGraphImpl): RecorderFeatureNavGraph
}
