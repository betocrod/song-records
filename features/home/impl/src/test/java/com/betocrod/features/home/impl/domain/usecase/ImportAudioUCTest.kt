package com.betocrod.features.home.impl.domain.usecase

import android.net.Uri
import com.betocrod.features.audios.impl.proxy.ContentResolverProxy
import com.betocrod.features.audios.impl.repository.AudioRepository
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
    lateinit var contentResolverProxy: com.betocrod.features.audios.impl.proxy.ContentResolverProxy

    @MockK
    lateinit var audioRepository: com.betocrod.features.audios.impl.repository.AudioRepository

    @MockK
    lateinit var importAudioUC: com.betocrod.features.audios.impl.usecase.ImportAudioUC

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        importAudioUC = com.betocrod.features.audios.impl.usecase.ImportAudioUC(
            audioRepository,
            contentResolverProxy
        )
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
