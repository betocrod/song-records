package com.betocrod.features.home.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.home.impl.R
import com.betocrod.features.home.impl.domain.models.Song
import com.betocrod.features.home.impl.model.HomeState
import com.betocrod.features.home.impl.widgets.previewparameters.SampleHomeStateProvider

@Composable
fun HomeContent(
    state: HomeState,
    onItemClick: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        HomeState.Error -> HomeContentError(modifier = modifier)
        is HomeState.SongsLoaded -> SongListWidget(
            modifier = modifier,
            songs = state.songs,
            onItemClicked = { onItemClick(it) }
        )
    }
}

@Composable
fun HomeContentError(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_home_error)
        )
    }
}

@Preview("Home Content")
@Composable
private fun PreviewHomeContent(
    @PreviewParameter(SampleHomeStateProvider::class) homeState: HomeState
) {
    SongRecordsTheme {
        HomeContent(state = homeState, onItemClick = {}, modifier = Modifier.fillMaxSize())
    }
}
