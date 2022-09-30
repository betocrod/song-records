package com.betocrod.features.home.impl.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.betocrod.designsystem.DSDrawable
import com.betocrod.designsystem.SongRecordsTheme
import com.betocrod.features.home.impl.R
import com.betocrod.features.audios.api.models.Song
import com.betocrod.features.home.impl.model.HomeState
import com.betocrod.features.home.impl.widgets.previewparameters.SampleHomeStateProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(
    homeState: HomeState,
    onItemClick: (Song) -> Unit,
    onImportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.feature_home_title)) })
        },
        content = {
            HomeContent(
                state = homeState,
                onItemClick = onItemClick,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(it)
                    .fillMaxSize()
            )
        },
        floatingActionButton = {
            ImportSongButton(onClick = { onImportClick() })
        }
    )
}

@Composable
private fun ImportSongButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        content = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = DSDrawable.ic_attach_file_add),
                contentDescription = null
            )
        }
    )
}

@Preview("Home Scaffold")
@Composable
private fun PreviewHomeScaffold(@PreviewParameter(SampleHomeStateProvider::class) homeState: HomeState) {
    SongRecordsTheme {
        HomeScaffold(homeState = homeState, onItemClick = {}, onImportClick = {})
    }
}
