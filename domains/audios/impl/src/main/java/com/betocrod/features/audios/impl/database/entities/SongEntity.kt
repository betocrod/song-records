package com.betocrod.features.audios.impl.database.entities

import androidx.room.Entity

@Entity
data class SongEntity(
    val id: String,
    val filePath: String
)
