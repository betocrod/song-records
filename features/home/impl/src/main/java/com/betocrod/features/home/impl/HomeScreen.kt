package com.betocrod.features.home.impl

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.home.impl.widgets.HomeScaffold

@Composable
fun HomeScreen(
    onItemClick: (Song) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            it?.let { viewModel.importAudio(it) }
        }
    val state by viewModel.state.collectAsState()
    HomeScaffold(
        modifier = modifier,
        onItemClick = onItemClick,
        homeState = state,
        onImportClick = { launcher.launch(AUDIO_MIME_TYPE) }
    )
}

private const val AUDIO_MIME_TYPE = "audio/*"
