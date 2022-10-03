package com.betocrod.features.recorder.impl

import android.Manifest.permission.RECORD_AUDIO
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.recorder.impl.widget.RecordScreenScaffold
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecorderScreen(
    onBackClick: () -> Unit,
    viewModel: RecorderViewModel = hiltViewModel()
) {
    val recordAudioPermission = rememberPermissionState(permission = RECORD_AUDIO)
    val state = viewModel.state
    RecordScreenScaffold(
        state = state,
        onRecordClick = {
            when (recordAudioPermission.status) {
                is PermissionStatus.Denied -> {
                    recordAudioPermission.launchPermissionRequest()
                }
                PermissionStatus.Granted -> {
                    viewModel.startRecording()
                }
            }
        },
        onStopClick = { viewModel.stopRecording() },
        onBackClick = onBackClick
    )
}
