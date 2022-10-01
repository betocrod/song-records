package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.models.Song

class SampleSongProvider : PreviewParameterProvider<Song> {
    override val values: Sequence<Song>
        get() {
            return getSongList().asSequence()
        }
}

internal fun getSongList() = (1..10).map {
    Song(
        title = "Song name #$it",
        artist = "Artist #$it",
        year = "2001",
        image = null,
        id = it,
        mediaData = MediaData("filePath")
    )
}
