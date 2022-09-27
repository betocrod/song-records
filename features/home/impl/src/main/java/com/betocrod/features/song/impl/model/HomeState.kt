package com.betocrod.features.song.impl.model

import com.betocrod.features.song.impl.models.Song

sealed class HomeState {

    class SongsLoaded(val songs: List<Song>) : HomeState()
    object Error : HomeState()
}
