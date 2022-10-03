package com.betocrod.domains.audios.wiring

import com.betocrod.features.audios.api.usecases.*
import com.betocrod.features.audios.impl.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AudiosWiringModule {

    @Binds
    abstract fun bindImportAudioUC(useCase: ImportAudioUCImpl): ImportAudioUC

    @Binds
    abstract fun bindGetSongsUC(useCase: GetSongsUCImpl): GetSongsUC

    @Binds
    abstract fun bindGetSongUC(useCase: FindSongUCImpl): FindSongUC

    @Binds
    abstract fun bindGetSongWithRecordsUC(useCase: FindSongWithRecordsUCImpl): FindSongWithRecordsUC

    @Binds
    abstract fun bindRecordUC(useCase: RecordUCImpl): RecordUC

    @Binds
    abstract fun bindRecordAudioUC(useCase: RecordAudioUCImpl): RecordAudioUC
}
