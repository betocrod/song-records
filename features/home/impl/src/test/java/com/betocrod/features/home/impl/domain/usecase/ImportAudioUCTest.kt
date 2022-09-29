package com.betocrod.features.home.impl.domain.usecase

import android.net.Uri
import com.betocrod.features.home.impl.domain.proxy.ContentResolverProxy
import com.betocrod.features.home.impl.domain.repository.AudioRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.File

class ImportAudioUCTest {

    @MockK
    lateinit var contentResolverProxy: ContentResolverProxy

    @MockK
    lateinit var audioRepository: AudioRepository

    @MockK
    lateinit var importAudioUC: ImportAudioUC

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        importAudioUC = ImportAudioUC(audioRepository, contentResolverProxy)
    }

    @Test
    fun invokingUCRetrievesAudioFromContentResolver() = runBlocking {
        val file = mockk<File>()
        val uri = mockk<Uri>()
        coEvery { contentResolverProxy.getAudioFile(uri) } returns file
        importAudioUC.invoke(uri)
        verify(exactly = 1) { audioRepository.save(file) }
    }
}
