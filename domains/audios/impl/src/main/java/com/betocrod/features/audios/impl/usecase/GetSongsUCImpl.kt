package com.betocrod.features.audios.impl.usecase

import android.media.MediaMetadataRetriever
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.audios.api.usecases.GetSongsUC
import com.betocrod.features.audios.impl.database.daos.SongDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSongsUCImpl @Inject constructor(
    private val songsDao: SongDao
) : GetSongsUC {

    override fun invoke(): Flow<List<Song>> {
        return songsDao.findAll()
            .map { entities ->
                val mediaMetadataRetriever = MediaMetadataRetriever()
                entities
                    .onEach { mediaMetadataRetriever.setDataSource(it.filePath) }
                    .map {
                        mediaMetadataRetriever.run {
                            Song(
                                name = extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).orEmpty(),
                                image = it.filePath,
                                artist = extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).orEmpty(),
                                year = extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)?.toInt()
                                    ?: -1
                            )
                        }
                    }
            }
    }
}
