package com.betocrod.features.song.impl.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.audios.api.models.MediaData
import com.betocrod.features.song.impl.R
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import com.betocrod.features.song.impl.models.SongState
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState
import com.betocrod.features.song.impl.widgets.previewparameters.SampleSongStateProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongScaffold(
    songState: SongState,
    playerProgress: Float,
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit,
    onPlayClick: (MediaData) -> Unit,
    onPauseClick: (MediaData) -> Unit,
    onProgressChange: (Float) -> Unit,
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
                onPlayClick = onPlayClick,
                onPauseClick = onPauseClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        },
        floatingActionButton = {
            RecordButton(onClick = { onRecordClick() })
        },
        bottomBar = {
            AnimatedVisibility(
                visible = LocalPlayerState.current != PlayerState.None,
                enter = expandIn(expandFrom = Alignment.BottomCenter)
            ) {
                BottomBar(
                    playerProgress = playerProgress,
                    onProgressChange = onProgressChange,
                    onPlayClick = onPlayClick,
                    onPauseClick = onPauseClick,
                    Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Composable
private fun BottomBar(
    playerProgress: Float,
    onProgressChange: (Float) -> Unit,
    onPlayClick: (MediaData) -> Unit,
    onPauseClick: (MediaData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider()
        Slider(
            modifier = Modifier.padding(horizontal = 16.dp),
            value = playerProgress,
            onValueChange = onProgressChange
        )
        when (val state = LocalPlayerState.current) {
            PlayerState.None -> Unit
            is PlayerState.Paused -> PlayMediaButton(
                onPlayClick = onPlayClick,
                onPauseClick = onPauseClick,
                mediaData = state.mediaData
            )
            is PlayerState.Playing -> PlayMediaButton(
                onPlayClick = onPlayClick,
                onPauseClick = onPauseClick,
                mediaData = state.mediaData
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
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
        SongScaffold(
            songState = songState,
            playerProgress = 0f,
            onBackClick = {},
            onRecordClick = {},
            onPlayClick = {},
            onPauseClick = {},
            onProgressChange = {}
        )
    }
}
