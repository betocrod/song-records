package com.betocrod.features.recorder.impl.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.designsystem.space.SmallSpace
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.recorder.impl.widget.previewparameters.SampleSongProvider

@Composable
fun SongWidget(song: Song, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.size(128.dp),
            model = song.image,
            placeholder = painterResource(id = DSDrawable.ic_image),
            contentDescription = null
        )
        SmallSpace()
        Text(
            text = song.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        SmallSpace()
        Text(
            text = song.artist,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        SmallSpace()
        Text(
            text = song.year,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview("Song Widget")
@Composable
fun PreviewSongWidget(@PreviewParameter(SampleSongProvider::class) song: Song) {
    SongRecordsTheme {
        SongWidget(song = song)
    }
}
