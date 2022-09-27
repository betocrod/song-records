@file:OptIn(ExperimentalMaterial3Api::class)

package com.betocrod.features.recorder.impl


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.designsystem.space.LargeSpace
import com.betocrod.features.recorder.impl.models.RecorderState
import com.betocrod.features.recorder.impl.widget.RecordButton
import com.betocrod.features.recorder.impl.widget.SongWidget
import com.betocrod.features.recorder.impl.widget.previewparameters.SampleRecorderStateProvider

@Composable
fun RecordScreenScaffold(
    state: RecorderState,
    onRecordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.feature_recorder_title)) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = DSDrawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            when (state) {
                RecorderState.Error -> ErrorContent(
                    Modifier
                        .padding(it)
                        .fillMaxSize()
                )
                is RecorderState.Success -> SuccessContent(
                    contentPadding = it,
                    state = state,
                    onRecordClick = onRecordClick
                )
            }

        }
    )
}

@Composable
private fun SuccessContent(
    contentPadding: PaddingValues,
    state: RecorderState.Success,
    onRecordClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
    ) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SongWidget(state.song)
            LargeSpace()
            RecordButton(state.recording, { onRecordClick() })
        }
        SongProgress(
            progress = state.progress,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ErrorContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.feature_home_error)
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

@Preview("Record Screen Scaffold")
@Composable
fun PreviewRecordScreenScaffold(@PreviewParameter(SampleRecorderStateProvider::class) recorderState: RecorderState) {
    SongRecordsTheme {
        RecordScreenScaffold(
            state = recorderState,
            onRecordClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
