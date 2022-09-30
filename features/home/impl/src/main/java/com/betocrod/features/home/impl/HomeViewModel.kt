package com.betocrod.features.home.impl

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betocrod.features.home.impl.domain.usecase.ImportAudioUC
import com.betocrod.features.home.impl.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val importAudioUC: ImportAudioUC
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun importAudio(audioContentUri: Uri) = viewModelScope.launch(coroutineExceptionHandler) {
        importAudioUC.invoke(audioContentUri)
    }

    val state: HomeState = HomeState.SongsLoaded(emptyList())
}
