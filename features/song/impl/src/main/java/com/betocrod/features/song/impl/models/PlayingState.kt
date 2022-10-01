package com.betocrod.features.song.impl.models

import com.betocrod.features.audios.api.models.MediaData

sealed class PlayingState {
    class Paused(val mediaData: MediaData) : PlayingState()
    class Playing(val mediaData: MediaData) : PlayingState()
    object None : PlayingState()
}
