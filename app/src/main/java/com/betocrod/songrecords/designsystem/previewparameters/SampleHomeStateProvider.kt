package com.betocrod.songrecords.designsystem.previewparameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.betocrod.songrecords.features.home.model.HomeState

class SampleHomeStateProvider : PreviewParameterProvider<HomeState> {
    override val values: Sequence<HomeState>
        get() = sequenceOf(
            HomeState.SongsLoaded(getSongList()),
            HomeState.Error
        )
}
