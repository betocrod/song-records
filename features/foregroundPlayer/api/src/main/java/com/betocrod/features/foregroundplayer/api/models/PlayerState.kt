package com.betocrod.features.foregroundplayer.api.models

import com.betocrod.features.audios.api.models.MediaData

sealed class PlayerState {
    class Paused(val mediaData: MediaData) : PlayerState()
    class Playing(val mediaData: MediaData) : PlayerState()
    object None : PlayerState()
}
