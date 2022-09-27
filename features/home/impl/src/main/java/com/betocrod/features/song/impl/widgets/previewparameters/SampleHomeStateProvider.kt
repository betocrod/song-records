package com.betocrod.features.song.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.song.impl.model.HomeState

class SampleHomeStateProvider : PreviewParameterProvider<HomeState> {
    override val values: Sequence<HomeState>
        get() = sequenceOf(
            HomeState.SongsLoaded(getSongList()),
            HomeState.Error
        )
}
