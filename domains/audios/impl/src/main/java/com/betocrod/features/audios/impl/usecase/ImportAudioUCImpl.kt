package com.betocrod.features.audios.impl.usecase

import android.net.Uri
import com.betocrod.features.audios.api.usecases.ImportAudioUC
import com.betocrod.features.audios.impl.proxy.ContentResolverProxy
import com.betocrod.features.audios.impl.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImportAudioUCImpl @Inject constructor(
    private val songRepository: SongRepository,
    private val contentResolverProxy: ContentResolverProxy
) : ImportAudioUC {

    override suspend operator fun invoke(audioContentUri: Uri) {
        withContext(Dispatchers.Default) {
            val file = contentResolverProxy.getAudioFile(audioContentUri)
            file?.let { songRepository.save(file) }
        }
    }
}
