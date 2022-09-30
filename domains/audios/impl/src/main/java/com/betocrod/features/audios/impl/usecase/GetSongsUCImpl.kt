package com.betocrod.features.audios.impl.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import com.betocrod.domains.audios.impl.R
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.api.usecases.GetSongsUC
import com.betocrod.features.audios.impl.database.daos.SongDao
import com.betocrod.features.audios.impl.database.entities.SongEntity
import com.betocrod.features.audios.impl.resource.AppResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject

class GetSongsUCImpl @Inject constructor(
    private val songsDao: SongDao,
    private val appResources: AppResources
) : GetSongsUC {

    override fun invoke(): Flow<List<Song>> {
        return songsDao.findAll()
            .map { entities -> mapSongs(entities) }
    }

    private fun mapSongs(entities: List<SongEntity>): List<Song> {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        return entities
            .asSequence()
            .onEach { mediaMetadataRetriever.setDataSource(it.filePath) }
            .map { mediaMetadataRetriever.extractMetadata(it) }
            .toList()
    }

    private fun MediaMetadataRetriever.extractMetadata(it: SongEntity) =
        Song(
            id = it.id,
            title = getTitle(it),
            filePath = it.filePath,
            artist = extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                ?: unknown(),
            year = extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)
                ?: unknown(),
            image = getBitmap()
        )

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
