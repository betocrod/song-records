package com.betocrod.features.recorder.impl

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.recorder.impl.widget.RecordScreenScaffold

@Composable
fun RecorderScreen(
    onBackClick: () -> Unit,
    viewModel: RecorderViewModel = hiltViewModel()
) {
    val state = viewModel.state
    RecordScreenScaffold(
        state = state,
        onRecordClick = { viewModel.startRecording() },
        onBackClick = onBackClick
    )
}
