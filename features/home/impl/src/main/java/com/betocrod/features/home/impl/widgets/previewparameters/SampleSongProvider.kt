package com.betocrod.features.home.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.models.Song

class SampleSongProvider : PreviewParameterProvider<Song> {
    override val values: Sequence<Song>
        get() {
            return getSongList().asSequence()
        }
}

class SampleSongListProvider : PreviewParameterProvider<List<Song>> {
    override val values: Sequence<List<Song>>
        get() = sequenceOf(
            getSongList(),
            emptyList()
        )
}

internal fun getSongList() = (1..10).map {
    Song(
        id = 0,
        title = "Song name #$it",
        artist = "Artist #$it",
        year = "2001",
        image = null,
        mediaData = MediaData(
            filePath = "filePath",
            title = "Song title #$it",
            content = null,
            bitmap = null
        )
    )
}
