package com.betocrod.features.home.impl.domain.usecase

import android.net.Uri
import com.betocrod.features.home.impl.domain.proxy.ContentResolverProxy
import com.betocrod.features.home.impl.domain.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImportAudioUC @Inject constructor(
    private val audioRepository: AudioRepository,
    private val contentResolverProxy: ContentResolverProxy
) {

    suspend operator fun invoke(audioContentUri: Uri) = withContext(Dispatchers.Default) {
        val file = contentResolverProxy.getAudioFile(audioContentUri)
        file?.let { audioRepository.save(file) }
    }
}
