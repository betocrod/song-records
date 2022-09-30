package com.betocrod.features.home.impl.widgets.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.features.home.impl.model.HomeState

class SampleHomeStateProvider : PreviewParameterProvider<HomeState> {
    override val values: Sequence<HomeState>
        get() = sequenceOf(
            HomeState.SongsLoaded(getSongList()),
            HomeState.Error,
            HomeState.Loading
        )
}
