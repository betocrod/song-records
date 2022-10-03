package com.betocrod.features.audios.api.usecases

interface RecordAudioUC {

    fun start()

    fun stop(): String
}
