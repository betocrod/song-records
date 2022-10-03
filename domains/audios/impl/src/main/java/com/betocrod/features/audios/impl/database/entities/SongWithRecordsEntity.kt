package com.betocrod.features.audios.impl.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SongWithRecordsEntity(
    @Embedded
    val song: SongEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "songId"
    )
    val records: List<RecordEntity>
)
