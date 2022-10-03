package com.betocrod.features.audios.impl.usecase

import com.betocrod.features.audios.api.models.SongWithRecords
import com.betocrod.features.audios.api.usecases.FindSongWithRecordsUC
import com.betocrod.features.audios.impl.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FindSongWithRecordsUCImpl @Inject constructor(
    private val songRepository: SongRepository
) : FindSongWithRecordsUC {
    override suspend fun invoke(songId: String): SongWithRecords =
        withContext(Dispatchers.Default) {
            songRepository.findSongWithRecords(songId)
        }
}
