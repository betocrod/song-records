package com.betocrod.features.song.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.betocrod.features.audios.api.usecases.FindSongUC
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SongViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val findSongUC: FindSongUC
): ViewModel() {


}
