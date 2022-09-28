package com.betocrod.features.song.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = DSDrawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = { Text(text = stringResource(R.string.feature_song_title)) }
            )
        },
        content = {
            SongContent(
                state = songState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            )
        },
        floatingActionButton = {
            RecordButton(onClick = { onRecordClick() })
        }
    )
}

@Composable
private fun RecordButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        content = {
            Icon(
                painter = painterResource(id = DSDrawable.ic_mic),
                contentDescription = null
            )
        },
    )
}

@Preview("Song Scaffold")
@Composable
private fun PreviewSongScaffold(@PreviewParameter(SampleSongStateProvider::class) songState: SongState) {
    SongRecordsTheme {
        SongScaffold(songState = songState, onBackClick = {}, onRecordClick = {})
    }
}
