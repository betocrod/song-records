package com.betocrod.features.song.impl

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.widgets.SongScaffold
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecordClick: (Int) -> Unit,
    onPlayAudio: () -> Unit
) {
    val exoplayer = viewModel.player
    val playerState by viewModel.playerState.collectAsState()
    val progress by viewModel.progress.collectAsState()

    CompositionLocalProvider(LocalPlayerState provides playerState) {
        SongScaffold(
            songState = viewModel.songState,
            playerProgress = progress,
            onBackClick = onBackClick,
            onRecordClick = onRecordClick,
            onPlayClick = {
                viewModel.play(it)
                onPlayAudio()
            },
            onPauseClick = { viewModel.pause(it) },
            onProgressChange = { exoplayer.seekTo((exoplayer.duration.toFloat() * it).toLong()) }
        )
    }
}
