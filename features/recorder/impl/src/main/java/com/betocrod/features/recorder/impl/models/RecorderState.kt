package com.betocrod.features.recorder.impl.models

import com.betocrod.features.audios.api.models.Song

sealed class RecorderState {

    class Success(val song: Song, val recording: Boolean, val progress: Float) : RecorderState()
    object Error : RecorderState()
    object Loading : RecorderState()
}
