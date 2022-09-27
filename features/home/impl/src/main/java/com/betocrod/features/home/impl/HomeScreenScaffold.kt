package com.betocrod.features.home.impl

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.betocrod.designsystem.DSDrawable
import com.betocrod.features.home.impl.model.HomeState
import com.betocrod.features.home.impl.widgets.HomeContent
import com.betocrod.features.home.impl.widgets.previewparameters.SampleHomeStateProvider
import com.betocrod.designsystem.SongRecordsTheme

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
                        painter = painterResource(id = DSDrawable.ic_image),
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
