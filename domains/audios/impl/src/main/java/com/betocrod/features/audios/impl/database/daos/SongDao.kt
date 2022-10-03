package com.betocrod.features.audios.impl.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.betocrod.features.audios.impl.database.entities.SongEntity
import com.betocrod.features.audios.impl.database.entities.SongWithRecordsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Insert
    suspend fun save(songEntity: SongEntity)

    @Query("SELECT * FROM SongEntity")
    fun findAll(): Flow<List<SongEntity>>


    @Query("SELECT * FROM SongEntity where id = :songId")
    suspend fun find(songId: String): SongEntity

    @Query("SELECT * FROM SongEntity where id = :songId")
    suspend fun findSongWithRecords(songId: String): SongWithRecordsEntity
}
