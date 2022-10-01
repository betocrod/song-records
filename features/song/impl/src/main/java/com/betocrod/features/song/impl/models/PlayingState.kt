package com.betocrod.features.song.impl.models

sealed class PlayingState {
    class Paused(val filePath: String) : PlayingState()
    class Playing(val filePath: String) : PlayingState()
    object None : PlayingState()
}
