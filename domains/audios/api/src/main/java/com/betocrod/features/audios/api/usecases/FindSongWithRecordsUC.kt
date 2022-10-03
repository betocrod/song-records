package com.betocrod.features.audios.api.usecases

import com.betocrod.features.audios.api.models.SongWithRecords

interface FindSongWithRecordsUC {

    suspend operator fun invoke(songId: String): SongWithRecords
}
