package com.betocrod.domains.audios.wiring

import com.betocrod.features.audios.api.usecases.ImportAudioUC
import com.betocrod.features.audios.impl.usecase.ImportAudioUCImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AudiosWiringModule {

    @Binds
    abstract fun bindImportAudioUC(useCase: ImportAudioUCImpl): ImportAudioUC
}
