package com.betocrod.features.song.impl

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.song.impl.widgets.SongScaffold

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit
) {
    SongScaffold(
        songState = viewModel.songState,
        onBackClick = onBackClick,
        onRecordClick = onRecordClick
    )
}
