package com.betocrod.features.audios.impl.usecase

import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.api.usecases.GetSongsUC
import com.betocrod.features.audios.impl.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSongsUCImpl @Inject constructor(
    private val repository: SongRepository
) : GetSongsUC {

    override fun invoke(): Flow<List<Song>> {
        return repository.findAllSongs()
    }
}
