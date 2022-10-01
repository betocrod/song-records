package com.betocrod.features.song.impl.widgets.compositionlocal

import androidx.compose.runtime.compositionLocalOf
import com.betocrod.features.song.impl.models.PlayerState

val LocalPlayerState = compositionLocalOf<PlayerState> { PlayerState.None }
