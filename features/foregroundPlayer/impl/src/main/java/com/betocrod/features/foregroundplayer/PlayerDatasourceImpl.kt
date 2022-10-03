package com.betocrod.features.foregroundplayer

import android.graphics.Bitmap
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayerDatasourceImpl @Inject constructor(
    private val exoPlayer: ExoPlayer
) : PlayerDatasource {

    private val _state = MutableStateFlow<PlayerState>(PlayerState.None)
    private val _progressState = MutableStateFlow(0f)

    override var currentData: MediaData? = null
        private set

    override val currentTitle: CharSequence
        get() = currentData?.title.orEmpty()

    override val currentContentText: CharSequence?
        get() = currentData?.content

    override val currentBitmap: Bitmap?
        get() = currentData?.bitmap

    override val state: StateFlow<PlayerState> = _state
    override val progressState: StateFlow<Float> = _progressState

    override suspend fun play(mediaData: MediaData) {
        this.currentData = mediaData
        emitState(PlayerState.Playing(mediaData))
    }

    override suspend fun pause(mediaData: MediaData) {
        this.currentData = mediaData
        emitState(PlayerState.Paused(mediaData))
    }

    override suspend fun stop() {
        this.currentData = null
        emitState(PlayerState.None)
    }

    override suspend fun updateProgress() {
        val currentPosition = withContext(Dispatchers.Main) { exoPlayer.currentPosition.toFloat() }
        val duration = withContext(Dispatchers.Main) { exoPlayer.duration.toFloat() }
        val progressPercent = (currentPosition / duration).takeIf { it != 1f } ?: 0f
        _progressState.emit(progressPercent)
    }

    private suspend fun emitState(state: PlayerState) {
        withContext(Dispatchers.Main) { exoPlayer.updateExoplayer(state) }
        _state.emit(state)
    }

    private fun ExoPlayer.updateExoplayer(state: PlayerState) {
        when (state) {
            PlayerState.None -> {
                stop()
                clearMediaItems()
            }
            is PlayerState.Playing -> {
                val item = MediaItem.fromUri(state.mediaData.filePath)
                addMediaItem(item)
                prepare()
                play()
            }
            is PlayerState.Paused -> pause()
        }
    }
}
