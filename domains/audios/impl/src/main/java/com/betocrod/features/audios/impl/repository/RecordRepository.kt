package com.betocrod.features.audios.impl.repository

import com.betocrod.features.audios.impl.database.daos.RecordDao
import com.betocrod.features.audios.impl.database.entities.RecordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecordRepository @Inject constructor(
    private val recordDao: RecordDao
) {
    suspend fun saveRecord(songId: Int, filePath: String) = withContext(Dispatchers.IO) {
        recordDao.save(RecordEntity(songId = songId, filePath = filePath))
    }
}
