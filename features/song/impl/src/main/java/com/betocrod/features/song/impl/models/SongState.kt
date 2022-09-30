package com.betocrod.features.song.impl.models

import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.song.impl.domain.models.Record

sealed class SongState {

    class SongData(val song: Song, val records: List<Record>) : SongState()
    object Error : SongState()
    object Loading : SongState()
}
