package com.betocrod.features.song.impl.models

import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.song.impl.domain.models.Record

sealed class SongState {

    data class SongData(val song: MediaData, val records: List<Record>) : SongState()
    object Error : SongState()
    object Loading : SongState()
}

data class MediaData(val song: Song, val playState: PlayState = PlayState.None)

sealed class PlayState {
    object None : PlayState()
    object Playing : PlayState()
    object Paused : PlayState()
}
