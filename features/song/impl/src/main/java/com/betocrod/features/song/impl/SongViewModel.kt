package com.betocrod.features.song.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.api.usecases.FindSongUC
import com.betocrod.features.song.impl.models.PlayingState
import com.betocrod.features.song.impl.models.SongState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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

    var playingState: PlayingState by mutableStateOf(PlayingState.None)
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

    fun playSong(song: Song) = viewModelScope
        .launch(CoroutineExceptionHandler { _, _ -> playingState = PlayingState.None }) {

        }

}
