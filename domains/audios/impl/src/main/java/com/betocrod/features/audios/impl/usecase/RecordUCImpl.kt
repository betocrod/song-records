package com.betocrod.features.audios.impl.usecase

import com.betocrod.features.audios.api.usecases.RecordAudioUC
import com.betocrod.features.audios.api.usecases.RecordUC
import com.betocrod.features.audios.impl.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecordUCImpl @Inject constructor(
    private val recordAudioUC: RecordAudioUC,
    private val recordRepository: RecordRepository
) : RecordUC {

    override fun start() {
        recordAudioUC.start()
    }

    override suspend fun stop(songId: Int) = withContext(Dispatchers.Default) {
        val filePath = recordAudioUC.stop()
        recordRepository.saveRecord(songId = songId, filePath = filePath)
    }
}
