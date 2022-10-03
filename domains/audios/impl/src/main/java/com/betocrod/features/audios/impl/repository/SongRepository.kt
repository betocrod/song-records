package com.betocrod.features.audios.impl.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import com.betocrod.domains.audios.impl.R
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.impl.database.daos.SongDao
import com.betocrod.features.audios.impl.database.entities.SongEntity
import com.betocrod.features.audios.impl.resource.AppResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val songDao: SongDao,
    private val appResources: AppResources
) {
    suspend fun save(file: File) = withContext(Dispatchers.IO) {
        songDao.save(SongEntity(filePath = file.path))
    }

    suspend fun findSong(songId: String): Song = withContext(Dispatchers.IO) {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        val entity = songDao.find(songId)
        mediaMetadataRetriever.setDataSource(entity.filePath)
        mediaMetadataRetriever.extractMetadata(entity)
    }

    fun findAllSongs(): Flow<List<Song>> {
        return songDao.findAll().map { mapSongs(it) }
    }

    private fun mapSongs(entities: List<SongEntity>): List<Song> {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        return entities
            .asSequence()
            .onEach { mediaMetadataRetriever.setDataSource(it.filePath) }
            .map { mediaMetadataRetriever.extractMetadata(it) }
            .toList()
    }

    private fun MediaMetadataRetriever.extractMetadata(it: SongEntity): Song {
        val title = getTitle(it)
        val bitmap = getBitmap()
        val artist = (extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            ?: unknown())
        val year = (extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)
            ?: unknown())
        return Song(
            id = it.id,
            title = title,
            artist = artist,
            year = year,
            image = bitmap,
            mediaData = MediaData(
                filePath = it.filePath,
                title = title,
                content = "$artist - $year",
                bitmap = bitmap
            )
        )
    }

    private fun MediaMetadataRetriever.getTitle(entity: SongEntity): String {
        return extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            ?: File(entity.filePath).nameWithoutExtension
    }

    private fun MediaMetadataRetriever.getBitmap(): Bitmap? {
        return embeddedPicture?.let {
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }
    }

    private fun unknown(): String {
        return appResources.getString(R.string.unknown)
    }
}
