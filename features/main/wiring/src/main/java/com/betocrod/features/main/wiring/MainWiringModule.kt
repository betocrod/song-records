package com.betocrod.features.main.wiring

import com.betocrod.features.main.api.AppGraphRenderer
import com.betocrod.features.main.impl.AppGraphRendererImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MainWiringModule {

    @Binds
    abstract fun bindRenderer(renderer: AppGraphRendererImpl): AppGraphRenderer
}
