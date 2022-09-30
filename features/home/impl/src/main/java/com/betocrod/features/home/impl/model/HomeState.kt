package com.betocrod.features.home.impl.model

import com.betocrod.features.audios.api.models.Song


sealed class HomeState {

    class SongsLoaded(val songs: List<Song>) : HomeState()
    object Error : HomeState()
    object Loading : HomeState()
}
