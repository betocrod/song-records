package com.betocrod.features.audios.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.betocrod.features.audios.impl.database.daos.SongDao
import com.betocrod.features.audios.impl.database.entities.SongEntity

@Database(entities = [SongEntity::class], version = 1)
abstract class SongRecordsDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
}
