package com.betocrod.features.recorder.impl.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.recorder.impl.models.Song
import com.betocrod.features.recorder.impl.widget.previewparameters.SampleSongProvider

@Composable
fun SongWidget(song: Song, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.size(64.dp),
            model = song.image,
            placeholder = painterResource(id = DSDrawable.ic_image),
            contentDescription = null
        )
        Text(text = song.name)
        Text(text = song.artist)
        Text(text = song.year.toString())
    }
}

@Preview("Song Widget")
@Composable
fun PreviewSongWidget(@PreviewParameter(SampleSongProvider::class) song: Song) {
    SongRecordsTheme {
        SongWidget(song = song)
    }
}
