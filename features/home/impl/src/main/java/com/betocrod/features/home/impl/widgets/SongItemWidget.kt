package com.betocrod.features.home.impl.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.betocrod.features.home.impl.widgets.previewparameters.SampleSongProvider

@Composable
fun SongItemWidget(
    song: Song,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SongImageWidget(song = song, modifier = Modifier.size(64.dp))
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
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
            }
        }
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

@Preview("Song Item Widget")
@Composable
internal fun PreviewSongItemWidget(
    @PreviewParameter(
        SampleSongProvider::class,
        1
    ) song: Song
) {
    SongRecordsTheme {
        SongItemWidget(song = song)
    }
}
