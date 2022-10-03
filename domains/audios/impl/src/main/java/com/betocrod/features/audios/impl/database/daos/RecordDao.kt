package com.betocrod.features.audios.impl.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.betocrod.features.audios.impl.database.entities.RecordEntity

@Dao
interface RecordDao {

    @Insert
    suspend fun save(record: RecordEntity)
}
