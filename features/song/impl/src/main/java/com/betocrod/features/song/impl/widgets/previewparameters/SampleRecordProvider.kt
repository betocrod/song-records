package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.song.impl.domain.models.Record

class SampleRecordProvider: PreviewParameterProvider<Record> {
    override val values: Sequence<Record>
        get() = sequenceOf(
            Record("March 25, 2022", "Vocal warming")
        )
}
