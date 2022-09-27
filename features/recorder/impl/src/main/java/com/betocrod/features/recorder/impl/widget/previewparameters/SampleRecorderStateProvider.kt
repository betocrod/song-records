package com.betocrod.features.recorder.impl.widget.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.recorder.impl.models.RecorderState
import com.betocrod.features.recorder.impl.models.Song

class SampleRecorderStateProvider : PreviewParameterProvider<RecorderState> {

    override val values: Sequence<RecorderState>
        get() = sequenceOf(
            RecorderState.Error,
            RecorderState.Success(
                song = getSong(),
                recording = false,
                progress = 0f
            ),
            RecorderState.Success(
                song = getSong(),
                recording = true,
                progress = 0.35f
            )
        )
}

class SampleRecorderStateSuccessProvider : PreviewParameterProvider<RecorderState> {

    override val values: Sequence<RecorderState>
        get() = sequenceOf(
            RecorderState.Success(
                song = getSong(),
                recording = false,
                progress = 0f
            ),
            RecorderState.Success(
                song = getSong(),
                recording = true,
                progress = 0.35f
            )
        )
}


fun getSong() =
    Song(name = "Song name", image = "https://image.com", artist = "Artist name", year = 2022)
