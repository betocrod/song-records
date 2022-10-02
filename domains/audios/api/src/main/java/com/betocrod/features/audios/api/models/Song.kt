package com.betocrod.features.audios.api.models

import android.graphics.Bitmap

data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val year: String,
    val image: Bitmap?,
    val mediaData: MediaData
)
