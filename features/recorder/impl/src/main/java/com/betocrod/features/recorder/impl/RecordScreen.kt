package com.betocrod.features.recorder.impl

import android.Manifest.permission.RECORD_AUDIO
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.recorder.impl.widget.RecordScreenScaffold
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecorderScreen(
    onBackClick: () -> Unit,
    onRecordClick: () -> Unit,
    viewModel: RecorderViewModel = hiltViewModel()
) {
    val recordAudioPermission = rememberPermissionState(permission = RECORD_AUDIO)
    val state = viewModel.state
    val progress by viewModel.progress.collectAsState()
    RecordScreenScaffold(
        state = state,
        progress = progress,
        onRecordClick = {
            when (recordAudioPermission.status) {
                is PermissionStatus.Denied -> {
                    recordAudioPermission.launchPermissionRequest()
                }
                PermissionStatus.Granted -> {
                    onRecordClick()
                    viewModel.startRecording()
                }
            }
        },
        onStopClick = { viewModel.stopRecording() },
        onBackClick = onBackClick
    )
}
