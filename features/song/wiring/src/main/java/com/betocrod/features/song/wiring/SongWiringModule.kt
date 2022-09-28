package com.betocrod.features.song.wiring

import com.betocrod.common.navigation.FeatureNavGraph
import com.betocrod.features.song.api.SongFeatureNavGraph
import com.betocrod.features.song.impl.SongFeatureNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class SongWiringModule {

    @Binds
    @IntoSet
    abstract fun bindFeatureNavGraph(featureNavGraph: SongFeatureNavGraphImpl): FeatureNavGraph

    @Binds
    abstract fun bindSongFeatureNavGraph(featureNavGraph: SongFeatureNavGraphImpl): SongFeatureNavGraph
}
