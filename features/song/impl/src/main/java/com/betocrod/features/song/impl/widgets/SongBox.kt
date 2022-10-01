package com.betocrod.features.song.impl.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongProvider

@Composable
fun SongBox(
    song: Song,
    onPlaySongClick: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SongImageWidget(song = song, modifier = Modifier.size(64.dp))
            Spacer(modifier = Modifier.size(8.dp))
            SongDescription(
                song = song,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(
                onClick = { onPlaySongClick(song) }
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
            text = song.title,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = song.artist,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = song.year,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(8.dp))
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("Song Card")
@Composable
fun PreviewSongCard(@PreviewParameter(SampleSongProvider::class, 1) song: Song) {
    SongRecordsTheme {
        SongBox(modifier = Modifier.fillMaxWidth(), song = song, onPlaySongClick = {})
    }
}

@Composable
private fun SongImageWidget(song: Song, modifier: Modifier = Modifier) {
    val imageBitmap = song.image?.asImageBitmap()
    if (imageBitmap != null) {
        Image(
            modifier = modifier,
            bitmap = imageBitmap,
            contentDescription = null
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(id = DSDrawable.ic_image),
            contentDescription = null
        )
    }
}
