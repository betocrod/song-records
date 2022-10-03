package com.betocrod.features.recorder.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.audios.api.usecases.RecordUC
import com.betocrod.features.foregroundplayer.api.PlayerDatasource
import com.betocrod.features.recorder.impl.models.RecorderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecorderViewModel @Inject constructor(
    private val findSongUC: FindSongUC,
    private val recordUC: RecordUC,
    private val playerDatasource: PlayerDatasource,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val songId = savedStateHandle
        .get<String>(RecorderFeatureNavGraphImpl.PARAM_SONG_ID)
        .orEmpty()

    private val _onRecordFinished = MutableSharedFlow<Unit>()
    val onRecordFinished: SharedFlow<Unit> = _onRecordFinished

    var state: RecorderState by mutableStateOf(RecorderState.Loading)
        private set

    val progress = playerDatasource.progressState

    init {
        stopCurrentlyPlayingSong()
        updateInitialState()
    }

    private fun stopCurrentlyPlayingSong() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ -> state = RecorderState.Error }) {
            playerDatasource.stop()
        }
    }

    private fun updateInitialState() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ -> state = RecorderState.Error }) {
            val song = findSongUC(songId)
            state = RecorderState.Success(
                song = song,
                recording = false,
            )
        }
    }

    fun startRecording() =
        viewModelScope.launch(CoroutineExceptionHandler { _, _ -> state = RecorderState.Error }) {
            (state as? RecorderState.Success)?.let {
                playerDatasource.play(it.song.mediaData)
                recordUC.start()
                state = it.copy(recording = true)
            }
        }

    fun stopRecording() = viewModelScope.launch {
        (state as? RecorderState.Success)?.let {
            playerDatasource.stop()
            recordUC.stop(songId.toInt())
            state = it.copy(recording = false)
            _onRecordFinished.emit(Unit)
        }
    }
}
