package com.betocrod.features.song.impl

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.foregroundplayer.api.models.PlayerState
import com.betocrod.features.song.impl.widgets.SongScaffold
import com.betocrod.features.song.impl.widgets.compositionlocal.LocalPlayerState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecordClick: (Int) -> Unit,
    onPlayAudio: () -> Unit
) {
    val exoplayer = viewModel.player
    val playerState by viewModel.playerState.collectAsState()

    LoopEffect(durationInMillis = TIME_TO_REFRESH_TIME_PROGRESS) {
        viewModel.updateProgress(exoplayer.currentPosition, exoplayer.duration)
    }

    LaunchedEffect(playerState) {
        exoplayer.updateExoplayer(playerState)
    }

    CompositionLocalProvider(LocalPlayerState provides playerState) {
        SongScaffold(
            songState = viewModel.songState,
            playerProgress = viewModel.progress,
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

@Composable
private fun LoopEffect(
    durationInMillis: Long,
    action: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        var running = true
        coroutineScope.launch {
            while (running) {
                action()
                delay(durationInMillis)
            }
        }
        onDispose { running = false }
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

private const val TIME_TO_REFRESH_TIME_PROGRESS = 200L
