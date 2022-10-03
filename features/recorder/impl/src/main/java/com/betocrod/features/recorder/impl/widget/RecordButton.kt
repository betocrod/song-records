package com.betocrod.features.recorder.impl.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.recorder.impl.widget.previewparameters.SamplePreviewProvider

@Composable
fun RecordButton(
    recording: Boolean,
    onRecordClick: () -> Unit,
    onStopClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.size(64.dp),
        onClick = { if (recording) onStopClick() else onRecordClick() },
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = if (recording) DSDrawable.ic_stop_circle else DSDrawable.ic_record),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    )
}

@Preview("Record Button")
@Composable
fun PreviewRecordButton(@PreviewParameter(SamplePreviewProvider::class) recording: Boolean) {
    SongRecordsTheme {
        RecordButton(recording = recording, onRecordClick = {}, onStopClick = {})
    }
}
