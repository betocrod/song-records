package com.betocrod.features.recorder.impl.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.recorder.impl.widget.previewparameters.SamplePreviewProvider

@Composable
fun RecordButton(
    recording: Boolean,
    onRecordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = { onRecordClick() },
        content = {
            Icon(
                painter = painterResource(id = if (recording) DSDrawable.ic_stop_circle else DSDrawable.ic_record),
                contentDescription = null
            )
        }
    )
}

@Preview("Record Button")
@Composable
fun PreviewRecordButton(@PreviewParameter(SamplePreviewProvider::class) recording: Boolean) {
    SongRecordsTheme {
        RecordButton(recording = recording, onRecordClick = {})
    }
}
