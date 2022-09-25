package com.betocrod.songrecords.features.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.songrecords.designsystem.previewparameters.SampleSongListProvider
import com.betocrod.songrecords.domain.models.Song
import com.betocrod.songrecords.ui.theme.SongRecordsTheme

@Composable
fun SongListWidget(
    songs: List<Song>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(8.dp)
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

@Preview("Song List Widget")
@Composable
fun PreviewSongListWidget(
    @PreviewParameter(SampleSongListProvider::class) songs: List<Song>
) {
    SongRecordsTheme {
        SongListWidget(songs = songs)
    }
}
