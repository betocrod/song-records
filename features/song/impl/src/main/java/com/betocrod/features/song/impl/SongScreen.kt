package com.betocrod.features.song.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.models.PlayingState
import com.betocrod.features.song.impl.widgets.SongScaffold
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

    LaunchedEffect(viewModel.playingState) {
        when(val state = viewModel.playingState) {
            PlayingState.None -> {
                exoplayer.apply {
                    stop()
                    clearMediaItems()
                }
            }
            is PlayingState.Playing -> {
                exoplayer.apply {
                    val item = MediaItem.fromUri(state.filePath)
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

    SongScaffold(
        songState = viewModel.songState,
        onBackClick = onBackClick,
        onRecordClick = onRecordClick,
        onPlaySongClick = { viewModel.playSong(it) }
    )
}
