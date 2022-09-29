package com.betocrod.features.home.impl

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.betocrod.features.home.impl.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    fun importAudio(audioContentUri: Uri) {
        TODO("Not yet implemented")
    }

    val state: HomeState = HomeState.SongsLoaded(emptyList())
}
