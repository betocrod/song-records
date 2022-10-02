package com.betocrod.features.recorder.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.recorder.impl.models.RecorderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecorderViewModel @Inject constructor(
    private val findSongUC: FindSongUC,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val songId = savedStateHandle
        .get<String>(RecorderFeatureNavGraphImpl.PARAM_SONG_ID)
        .orEmpty()

    var state: RecorderState by mutableStateOf(RecorderState.Loading)
        private set

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ -> state = RecorderState.Error }) {
            val song = findSongUC(songId)
            state = RecorderState.Success(
                song = song,
                recording = false,
                progress = 0f
            )
        }
    }
}
