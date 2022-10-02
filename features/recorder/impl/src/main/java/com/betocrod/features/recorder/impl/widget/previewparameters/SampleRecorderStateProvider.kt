package com.betocrod.features.recorder.impl.widget.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.recorder.impl.models.RecorderState

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
            getSuccessState(),
            RecorderState.Success(
                song = getSong(),
                recording = true,
                progress = 0.35f
            )
        )
}


fun getSong() =
    Song(
        title = "Song name",
        image = null,
        artist = "Artist name",
        year = "2022",
        id = 0,
        mediaData = MediaData(filePath = "filePath", title = "Title", content = null, bitmap = null)
    )

fun getSuccessState() = RecorderState.Success(
    song = getSong(),
    recording = false,
    progress = 0f
)
