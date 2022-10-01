package com.betocrod.features.song.impl

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.models.PlayerState
import com.betocrod.features.song.impl.widgets.SongScaffold
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val exoplayer = remember { ExoPlayer.Builder(context).build() }
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        exoplayer.repeatMode = Player.REPEAT_MODE_OFF
    }

    DisposableEffect(exoplayer) {
        onDispose { exoplayer.release() }
    }

    DisposableEffect(Unit) {
        var running = true
        coroutineScope.launch {
            while (running) {
                val currentPosition = exoplayer.currentPosition.toFloat()
                val duration = exoplayer.duration.toFloat()
                progress = (currentPosition / duration).takeIf { it != 1f } ?: 0f
                delay(200)
            }
        }
        onDispose { running = false }
    }

    LaunchedEffect(viewModel.playerState) {
        exoplayer.updateExoplayer(viewModel.playerState)
    }

    CompositionLocalProvider(LocalPlayerState provides viewModel.playerState) {
        SongScaffold(
            songState = viewModel.songState,
            playerProgress = progress,
            onBackClick = onBackClick,
            onRecordClick = onRecordClick,
            onPlayClick = { viewModel.play(it) },
            onPauseClick = { viewModel.pause(it) },
            onProgressChange = { exoplayer.seekTo((exoplayer.duration.toFloat() * it).toLong()) }
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
