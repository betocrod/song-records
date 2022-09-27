package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.song.impl.models.SongState

class SampleSongStateProvider : PreviewParameterProvider<SongState> {

    override val values: Sequence<SongState>
        get() = sequenceOf(
            SongState.Error,
            SongState.SongData(
                song = getSongList().first(),
                records = emptyList()
            ),
            SongState.SongData(
                song = getSongList().first(),
                records = getRecords()
            )
        )
}
