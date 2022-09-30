package com.betocrod.features.audios.impl.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val filePath: String
)
