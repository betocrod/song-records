package com.betocrod.features.song.impl.widgets.compositionlocal

import androidx.compose.runtime.compositionLocalOf
import com.betocrod.features.song.impl.models.PlayingState

val LocalPlayerState = compositionLocalOf<PlayingState> { PlayingState.None }
