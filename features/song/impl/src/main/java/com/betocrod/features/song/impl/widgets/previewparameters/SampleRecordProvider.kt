package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.song.impl.domain.models.Record

class SampleRecordProvider : PreviewParameterProvider<Record> {
    override val values: Sequence<Record>
        get() = sequenceOf(
            Record("March 25, 2022", "Vocal warming")
        )
}

class SampleRecordListProvider : PreviewParameterProvider<List<Record>> {

    override val values: Sequence<List<Record>>
        get() = sequenceOf(
            (0..15).map { Record("March $it, 2022", "Vocal warming $it") },
            listOf()
        )

}
