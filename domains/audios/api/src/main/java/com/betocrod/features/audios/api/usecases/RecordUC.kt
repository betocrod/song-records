package com.betocrod.features.audios.api.usecases

interface RecordUC {

    fun start()

    suspend fun stop(songId: Int)
}
