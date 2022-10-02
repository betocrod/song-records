package com.betocrod.features.foregroundplayer

import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PlayerDatasourceImpl @Inject constructor() : PlayerDatasource {

    private val _state = MutableStateFlow<PlayerState>(PlayerState.None)
    override val state: StateFlow<PlayerState> = _state

    override suspend fun play(mediaData: MediaData) {
        _state.emit(PlayerState.Playing(mediaData))
    }

    override suspend fun pause(mediaData: MediaData) {
        _state.emit(PlayerState.Paused(mediaData))
    }
}
