package com.betocrod.features.home.impl

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.audios.api.usecases.GetSongsUC
import com.betocrod.features.audios.api.usecases.ImportAudioUC
import com.betocrod.features.home.impl.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val importAudioUC: ImportAudioUC,
    getSongs: GetSongsUC
) : ViewModel() {

    val state: StateFlow<HomeState> = getSongs()
        .map { HomeState.SongsLoaded(it) }
        .catch { HomeState.Error }
        .stateIn(viewModelScope, SharingStarted.Lazily, HomeState.Loading)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun importAudio(audioContentUri: Uri) = viewModelScope.launch(coroutineExceptionHandler) {
        importAudioUC.invoke(audioContentUri)
    }
}
