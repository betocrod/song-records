package com.betocrod.songrecords.features.home

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.songrecords.R
import com.betocrod.songrecords.designsystem.previewparameters.SampleHomeStateProvider
import com.betocrod.songrecords.features.home.model.HomeState
import com.betocrod.songrecords.features.home.widgets.HomeContent
import com.betocrod.songrecords.ui.theme.SongRecordsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(
    homeState: HomeState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.feature_home_title)) })
        },
        content = { HomeContent(state = homeState, contentPadding = it) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { TODO("Not implemented") },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )
                },
                shape = CircleShape
            )
        }
    )
}

@Preview("Home Scaffold")
@Composable
private fun PreviewHomeScaffold(@PreviewParameter(SampleHomeStateProvider::class) homeState: HomeState) {
    SongRecordsTheme {
        HomeScaffold(homeState = homeState)
    }
}
