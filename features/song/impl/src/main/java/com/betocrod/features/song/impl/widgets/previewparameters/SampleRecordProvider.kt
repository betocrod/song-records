package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.audios.api.models.Record

class SampleRecordProvider : PreviewParameterProvider<Record> {
    override val values: Sequence<Record>
        get() = sequenceOf(
            Record(
                id = 0,
                title = "Vocal warming",
                mediaData = MediaData(
                    filePath = "filePath",
                    title = "title",
                    content = null,
                    bitmap = null
                )
            )
        )
}

class SampleRecordListProvider : PreviewParameterProvider<List<Record>> {

    override val values: Sequence<List<Record>>
        get() = sequenceOf(
            getRecords(),
            listOf()
        )
}

fun getRecords() = (0..15).map {
    Record(
        id = it,
        title = "Vocal warming $it",
        mediaData = MediaData(
            filePath = "filePath",
            title = "title",
            content = null,
            bitmap = null
        )
    )
}
