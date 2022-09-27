package com.betocrod.features.song.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.song.impl.models.SongState
import com.betocrod.features.song.impl.widgets.SongContent
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongStateProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongScaffold(
    songState: SongState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.feature_song_title)) })
        },
        content = {
            SongContent(
                state = songState,
                contentPadding = it,
                modifier = Modifier.fillMaxSize()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { TODO("Not implemented") },
                content = {
                    Icon(
                        painter = painterResource(id = DSDrawable.ic_mic),
                        contentDescription = null
                    )
                },
                shape = CircleShape
            )
        }
    )
}

@Preview("Song Scaffold")
@Composable
private fun PreviewSongScaffold(@PreviewParameter(SampleSongStateProvider::class) songState: SongState) {
    SongRecordsTheme {
        SongScaffold(songState = songState)
    }
}
