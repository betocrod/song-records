package com.betocrod.features.song.impl

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.song.impl.models.SongState
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val findSongUC: FindSongUC,
    private val playerDatasource: PlayerDatasource,
    val player: ExoPlayer,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val songId = savedStateHandle
        .get<String>(SongFeatureNavGraphImpl.PARAM_SONG_ID)
        .orEmpty()

    var songState: SongState by mutableStateOf(SongState.Loading)
        private set

    var playerState = playerDatasource.state

    var progress = playerDatasource.progressState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            songState = SongState.Error
        }) {
            songState = try {
                SongState.SongData(findSongUC(songId), emptyList())
            } catch (ex: CancellationException) {
                SongState.Error
            }
        }
    }

    fun play(mediaData: MediaData) = viewModelScope
        .launch(CoroutineExceptionHandler { _, ex -> Log.e(TAG, ex.stackTraceToString()) }) {
            playerDatasource.play(mediaData)
        }

    fun pause(mediaData: MediaData) = viewModelScope
        .launch(CoroutineExceptionHandler { _, ex -> Log.e(TAG, ex.stackTraceToString()) }) {
            playerDatasource.pause(mediaData)
        }

    companion object {
        private const val TAG = "SongViewModel::class"
    }
}
