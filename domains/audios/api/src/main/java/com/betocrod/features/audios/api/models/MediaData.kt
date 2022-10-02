package com.betocrod.features.audios.api.models

import android.graphics.Bitmap

data class MediaData(
    val filePath: String,
    val title: String,
    val content: String?,
    val bitmap: Bitmap?
)
