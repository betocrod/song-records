package com.betocrod.features.foregroundplayer

import android.graphics.Bitmap
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PlayerDatasourceImpl @Inject constructor() : PlayerDatasource {

    private val _state = MutableStateFlow<PlayerState>(PlayerState.None)
    private var mediaData: MediaData? = null

    override val currentTitle: CharSequence
        get() = mediaData?.title.orEmpty()

    override val currentContentText: CharSequence?
        get() = mediaData?.content

    override val currentBitmap: Bitmap?
        get() = mediaData?.bitmap

    override val state: StateFlow<PlayerState> = _state

    override suspend fun play(mediaData: MediaData) {
        this.mediaData = mediaData
        _state.emit(PlayerState.Playing(mediaData))
    }

    override suspend fun pause(mediaData: MediaData) {
        this.mediaData = mediaData
        _state.emit(PlayerState.Paused(mediaData))
    }
}
