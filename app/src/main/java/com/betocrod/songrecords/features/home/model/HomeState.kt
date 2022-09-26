package com.betocrod.songrecords.features.home.model

import com.betocrod.songrecords.domain.models.Song

sealed class HomeState {

    class SongsLoaded(val songs: List<Song>) : HomeState()
    object Error : HomeState()
}
