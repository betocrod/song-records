package com.betocrod.features.recorder.impl.widget.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.audios.api.models.Song

class SampleSongProvider : PreviewParameterProvider<Song> {

    override val values: Sequence<Song>
        get() = sequenceOf(getSong())
}
