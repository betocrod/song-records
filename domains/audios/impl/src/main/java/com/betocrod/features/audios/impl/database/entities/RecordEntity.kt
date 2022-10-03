package com.betocrod.features.audios.impl.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = SongEntity::class,
            parentColumns = ["id"],
            childColumns = ["songId"],
            onDelete = CASCADE
        )
    ]
)
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val songId: Int,
    val filePath: String
)
