package com.betocrod.features.audios.impl.usecase

import com.betocrod.features.audios.api.usecases.RecordAudioUC
import com.betocrod.features.audios.api.usecases.RecordUC
import javax.inject.Inject

class RecordUCImpl @Inject constructor(
    private val recordAudioUC: RecordAudioUC
) : RecordUC {

    override fun start() {
        recordAudioUC.start()
    }


    override fun stop() {
        recordAudioUC.stop()
    }
}
