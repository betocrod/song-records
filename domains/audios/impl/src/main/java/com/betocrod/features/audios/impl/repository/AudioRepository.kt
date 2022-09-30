package com.betocrod.features.audios.impl.repository

import com.betocrod.features.audios.impl.database.daos.SongDao
import com.betocrod.features.audios.impl.database.entities.SongEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val songDao: SongDao
) {
    suspend fun save(file: File) = withContext(Dispatchers.IO) {
        songDao.save(SongEntity(filePath = file.path))
    }
}
