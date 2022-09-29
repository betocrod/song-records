package com.betocrod.features.home.impl.domain.usecase

import android.net.Uri
import com.betocrod.features.home.impl.domain.proxy.ContentResolverProxy
import com.betocrod.features.home.impl.domain.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImportAudioUC(
    private val audioRepository: AudioRepository,
    private val contentResolverProxy: ContentResolverProxy) {

    suspend operator fun invoke(audioContentUri: Uri) = withContext(Dispatchers.Default) {
        val file  =contentResolverProxy.getAudioFile(audioContentUri)
        audioRepository.save(file)
    }
}
