package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.song.impl.domain.models.Song
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongProvider

@Composable
fun SongBox(song: Song, modifier: Modifier) {
    Box(modifier) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(song.image)
                    .error(DSDrawable.ic_image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = DSDrawable.ic_image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            SongDescription(
                song = song,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(
                onClick = { TODO("Not yet implemented") }
            ) {
                Icon(
                    painter = painterResource(id = DSDrawable.ic_play_circle),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun SongDescription(song: Song, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = song.name,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = StringBuilder().apply {
                append(song.artist)
                append(" - ")
                append(song.year)
            }.toString()
        )
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("Song Card")
@Composable
fun PreviewSongCard(@PreviewParameter(SampleSongProvider::class, 1) song: Song) {
    SongRecordsTheme {
        SongBox(modifier = Modifier.fillMaxWidth(), song = song)
    }
}
