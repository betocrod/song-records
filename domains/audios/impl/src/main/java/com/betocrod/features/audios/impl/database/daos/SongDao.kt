package com.betocrod.features.audios.impl.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.betocrod.features.audios.impl.database.entities.SongEntity

@Dao
abstract class SongDao {

    @Insert
    abstract fun save(songEntity: SongEntity)
}
