package com.betocrod.features.home.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.features.home.impl.R
import com.betocrod.features.home.impl.domain.models.Song
import com.betocrod.features.home.impl.widgets.previewparameters.SampleSongListProvider
import com.betocrod.designsystem.SongRecordsTheme

@Composable
fun SongListWidget(
    songs: List<Song>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    if (songs.isNotEmpty()) {
        NotEmptyListWidget(songs = songs, modifier = modifier, contentPadding = contentPadding)
    } else {
        EmptyListPlaceHolder(modifier)
    }
}

@Composable
private fun NotEmptyListWidget(
    songs: List<Song>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        contentPadding = contentPadding
    ) {
        items(songs) {
            SongItemWidget(
                song = it,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun EmptyListPlaceHolder(modifier: Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_home_no_songs_placeholder)
        )
    }
}

@Preview("Song List Widget")
@Composable
fun PreviewSongListWidget(
    @PreviewParameter(SampleSongListProvider::class) songs: List<Song>
) {
    SongRecordsTheme {
        SongListWidget(
            songs = songs,
            modifier = Modifier.fillMaxSize()
        )
    }
}
