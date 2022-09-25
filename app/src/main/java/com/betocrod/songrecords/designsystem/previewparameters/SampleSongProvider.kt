package com.betocrod.songrecords.designsystem.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.songrecords.domain.models.Song

class SampleSongProvider: PreviewParameterProvider<Song> {
    override val values: Sequence<Song>
        get() {
            return (1..10).map {
                Song(
                    name = "Song name #$it",
                    image = "https://www.imageurl.com",
                    artist = "Artist #$it",
                    year = 2001
                )
            }.asSequence()
        }
}
