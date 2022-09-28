package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.song.impl.domain.models.Song

class SampleSongProvider : PreviewParameterProvider<Song> {
    override val values: Sequence<Song>
        get() {
            return getSongList().asSequence()
        }
}

internal fun getSongList() = (1..10).map {
    Song(
        name = "Song name #$it",
        image = "https://www.imageurl.com",
        artist = "Artist #$it",
        year = 2001
    )
}
