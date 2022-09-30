package com.betocrod.features.audios.api.usecases

import android.net.Uri

interface ImportAudioUC {

    suspend operator fun invoke(audioContentUri: Uri)
}
