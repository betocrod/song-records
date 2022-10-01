package com.betocrod.features.song.impl

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.models.PlayingState
import com.betocrod.features.song.impl.widgets.SongScaffold
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit
) {
    val context = LocalContext.current
    val exoplayer = remember { ExoPlayer.Builder(context).build() }

    DisposableEffect(exoplayer) {
        onDispose { exoplayer.release() }
    }

    LaunchedEffect(viewModel.playerState) {
        when (val state = viewModel.playerState) {
            PlayingState.None -> {
                exoplayer.apply {
                    stop()
                    clearMediaItems()
                }
            }
            is PlayingState.Playing -> {
                exoplayer.apply {
                    val item = MediaItem.fromUri(state.mediaData.filePath)
                    exoplayer.addMediaItem(item)
                    exoplayer.prepare()
                    exoplayer.play()
                }
            }
            is PlayingState.Paused -> {
                exoplayer.pause()
            }
        }
    }
    CompositionLocalProvider(LocalPlayerState provides viewModel.playerState) {
        SongScaffold(
            songState = viewModel.songState,
            onBackClick = onBackClick,
            onRecordClick = onRecordClick,
            onPlayClick = { viewModel.play(it) },
            onPauseClick = { viewModel.pause(it) }
        )
    }
}
