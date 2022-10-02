package com.betocrod.features.foregroundplayer.wiring

import android.content.Context
import com.betocrod.features.foregroundplayer.PlayerDatasourceImpl
import com.betocrod.features.foregroundplayer.PlayerServiceLauncherImpl
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.foregroundplayer.api.PlayerServiceLauncher
import com.google.android.exoplayer2.ExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ForegroundPlayerWiringModule {

    @Binds
    abstract fun bindPlayerServiceLauncher(launcherImpl: PlayerServiceLauncherImpl): PlayerServiceLauncher

    @Binds
    @Singleton
    abstract fun bindPlayerDatasource(impl: PlayerDatasourceImpl): PlayerDatasource

    companion object {

        @Provides
        @Singleton
        fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
            return ExoPlayer.Builder(context).build()
        }
    }
}
