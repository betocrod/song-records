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

    override var currentData: MediaData? = null
        private set

    override val currentTitle: CharSequence
        get() = currentData?.title.orEmpty()

    override val currentContentText: CharSequence?
        get() = currentData?.content

    override val currentBitmap: Bitmap?
        get() = currentData?.bitmap

    override val state: StateFlow<PlayerState> = _state

    override suspend fun play(mediaData: MediaData) {
        this.currentData = mediaData
        _state.emit(PlayerState.Playing(mediaData))
    }

    override suspend fun pause(mediaData: MediaData) {
        this.currentData = mediaData
        _state.emit(PlayerState.Paused(mediaData))
    }
}
