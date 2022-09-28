package com.betocrod.features.recorder.impl.models

sealed class RecorderState {

    class Success(val song: Song, val recording: Boolean, val progress: Float) : RecorderState()
    object Error : RecorderState()
}
