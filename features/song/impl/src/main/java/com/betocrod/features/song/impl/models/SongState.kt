package com.betocrod.features.song.impl.models

import com.betocrod.features.song.impl.domain.models.Record
import com.betocrod.features.song.impl.domain.models.Song

sealed class SongState {

    class SongData(val song: Song, val records: List<Record>): SongState()
    object Error: SongState()
}
