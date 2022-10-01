package com.betocrod.features.song.impl

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.models.PlayerState
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
        exoplayer.updateExoplayer(viewModel.playerState)
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

private fun ExoPlayer.updateExoplayer(state: PlayerState) {
    when (state) {
        PlayerState.None -> {
            stop()
            clearMediaItems()
        }
        is PlayerState.Playing -> {
            val item = MediaItem.fromUri(state.mediaData.filePath)
            addMediaItem(item)
            prepare()
            play()
        }
        is PlayerState.Paused -> pause()
    }
}
