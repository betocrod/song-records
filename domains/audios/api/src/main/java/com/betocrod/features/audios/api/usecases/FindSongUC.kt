package com.betocrod.features.audios.api.usecases

import com.betocrod.features.audios.api.models.Song

interface FindSongUC {

    suspend operator fun invoke(songId: String): Song
}
