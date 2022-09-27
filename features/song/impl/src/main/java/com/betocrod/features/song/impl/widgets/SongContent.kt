package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.song.impl.R
import com.betocrod.features.song.impl.domain.models.Record
import com.betocrod.features.song.impl.domain.models.Song
import com.betocrod.features.song.impl.models.SongState
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongStateProvider

@Composable
fun SongContent(state: SongState, modifier: Modifier = Modifier) {
    when (state) {
        SongState.Error -> SongContentError(modifier)
        is SongState.SongData -> ContentWidget(state.song, state.records, modifier)
    }
}

@Composable
fun ContentWidget(song: Song, records: List<Record>, modifier: Modifier) {
    Column(modifier) {
        SongBox(song = song, modifier = Modifier.fillMaxWidth())
        Divider()
        RecordListWidget(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            records = records,
            onMoreClick = {}
        )
    }
}

@Composable
fun SongContentError(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
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
