package com.betocrod.features.song.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.song.impl.models.SongState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val findSongUC: FindSongUC,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val songId = savedStateHandle
        .get<String>(SongFeatureNavGraphImpl.PARAM_SONG_ID)
        .orEmpty()

    var songState: SongState by mutableStateOf(SongState.Loading)
        private set

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
}
