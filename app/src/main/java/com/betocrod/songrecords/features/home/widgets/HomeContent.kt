package com.betocrod.songrecords.features.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.songrecords.R
import com.betocrod.songrecords.designsystem.previewparameters.SampleHomeStateProvider
import com.betocrod.songrecords.features.home.model.HomeState
import com.betocrod.songrecords.ui.theme.SongRecordsTheme

@Composable
fun HomeContent(state: HomeState, modifier: Modifier = Modifier) {
    when (state) {
        HomeState.Error -> HomeContentError(modifier = modifier)
        is HomeState.SongsLoaded -> SongListWidget(modifier = modifier, songs = state.songs)
    }
}

@Composable
private fun HomeContentError(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_home_error)
        )
    }
}

@Preview("Home Content")
@Composable
private fun PreviewHomeContent(@PreviewParameter(SampleHomeStateProvider::class) homeState: HomeState) {
    SongRecordsTheme {
        HomeContent(state = homeState, modifier = Modifier.fillMaxSize())
    }
}
