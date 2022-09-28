package com.betocrod.features.recorder.impl


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.betocrod.features.recorder.impl.models.RecorderState
import com.betocrod.features.recorder.impl.widget.RecorderContent
import com.betocrod.features.recorder.impl.widget.previewparameters.SampleRecorderStateProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordScreenScaffold(
    state: RecorderState,
    onRecordClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.feature_recorder_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
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
                is RecorderState.Success -> RecorderContent(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = it,
                    state = state,
                    onRecordClick = onRecordClick
                )
            }

        }
    )
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

@Preview("Record Screen Scaffold")
@Composable
fun PreviewRecordScreenScaffold(@PreviewParameter(SampleRecorderStateProvider::class) recorderState: RecorderState) {
    SongRecordsTheme {
        RecordScreenScaffold(
            state = recorderState,
            onRecordClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
