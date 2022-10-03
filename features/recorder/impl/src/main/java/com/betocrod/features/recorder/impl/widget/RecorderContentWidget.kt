package com.betocrod.features.recorder.impl.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.designsystem.space.LargeSpace
import com.betocrod.features.recorder.impl.models.RecorderState
import com.betocrod.features.recorder.impl.widget.previewparameters.SampleRecorderStateSuccessProvider

@Composable
fun RecorderContent(
    state: RecorderState.Success,
    progress: Float,
    onRecordClick: () -> Unit,
    onStopClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SongWidget(state.song, Modifier.padding(horizontal = 32.dp))
            LargeSpace()
            RecordButton(
                recording = state.recording,
                onRecordClick = onRecordClick,
                onStopClick = onStopClick
            )
        }
        SongProgress(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun SongProgress(progress: Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        modifier = modifier,
        progress = progress
    )
}

@Preview("Recorder Content Widget")
@Composable
fun PreviewRecorderContent(@PreviewParameter(SampleRecorderStateSuccessProvider::class) state: RecorderState.Success) {
    SongRecordsTheme {
        RecorderContent(state = state, onRecordClick = {}, onStopClick = {}, progress = 0.5f)
    }
}
