package com.betocrod.features.audios.api.usecases

import com.betocrod.features.audios.api.models.Song
import kotlinx.coroutines.flow.Flow

interface GetSongsUC {

    operator fun invoke(): Flow<List<Song>>
}
