package com.betocrod.features.audios.impl.usecase

import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.audios.impl.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FindSongUCImpl @Inject constructor(
    private val audioRepository: AudioRepository
) : FindSongUC {

    override suspend fun invoke(songId: String): Song = withContext(Dispatchers.Default) {
        audioRepository.findSong(songId)
    }
}
