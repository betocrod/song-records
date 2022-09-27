package com.betocrod.features.recorder.impl.widget.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SamplePreviewProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}
