package com.betocrod.songrecords.features.home.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betocrod.songrecords.R
import com.betocrod.songrecords.designsystem.previewparameters.SampleSongProvider
import com.betocrod.songrecords.domain.models.Song
import com.betocrod.songrecords.ui.theme.SongRecordsTheme

@Composable
fun SongItemWidget(song: Song, modifier: Modifier = Modifier) {
    Card(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = song.image,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = song.name,
                    modifier = Modifier.fillMaxWidth()
                )
                SongDescription(
                    song = song,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun SongDescription(song: Song, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = StringBuilder().apply {
            append(song.artist)
            append(" - ")
            append(song.year)
        }.toString()
    )
}

@Preview("Song Item Widget")
@Composable
internal fun PreviewSongItemWidget(@PreviewParameter(SampleSongProvider::class, 1) song: Song) {
    SongRecordsTheme {
        SongItemWidget(song = song)
    }
}
