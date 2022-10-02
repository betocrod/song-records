package com.betocrod.features.foregroundplayer.api

import android.graphics.Bitmap
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import kotlinx.coroutines.flow.StateFlow

interface PlayerDatasource {

    val currentTitle: CharSequence
    val currentContentText: CharSequence?
    val currentBitmap: Bitmap?

    val state: StateFlow<PlayerState>
    suspend fun play(mediaData: MediaData)
    suspend fun pause(mediaData: MediaData)
}
