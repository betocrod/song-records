package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.song.impl.R
import com.betocrod.features.song.impl.domain.models.Record
import com.betocrod.features.song.impl.models.SongState
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongStateProvider

@Composable
fun SongContent(
    state: SongState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    when (state) {
        SongState.Error -> SongContentError(modifier, contentPadding)
        is SongState.SongData -> ContentWidget(state.song, state.records, modifier, contentPadding)
        SongState.Loading -> ContentLoader(modifier)
    }
}

@Composable
fun ContentLoader(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Green
        )
    }
}

@Composable
fun ContentWidget(
    song: Song,
    records: List<Record>,
    modifier: Modifier,
    contentPadding: PaddingValues
) {
    Column(modifier) {
        SongBox(song = song, modifier = Modifier.fillMaxWidth())
        Divider()
        RecordListWidget(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = contentPadding,
            records = records,
            onMoreClick = {}
        )
    }
}

@Composable
fun SongContentError(modifier: Modifier = Modifier, contentPadding: PaddingValues) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(contentPadding)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_song_error)
        )
    }
}

@Preview("Home Content")
@Composable
private fun PreviewSongContent(
    @PreviewParameter(SampleSongStateProvider::class) songState: SongState
) {
    SongRecordsTheme {
        SongContent(state = songState, modifier = Modifier.fillMaxSize())
    }
}
