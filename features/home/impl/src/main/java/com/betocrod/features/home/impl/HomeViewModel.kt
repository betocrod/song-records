package com.betocrod.features.home.impl

import androidx.lifecycle.ViewModel
import com.betocrod.features.home.impl.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val state: HomeState = HomeState.SongsLoaded(emptyList())
}
