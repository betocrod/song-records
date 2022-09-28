package com.betocrod.features.home.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.betocrod.features.home.impl.domain.models.Song
import com.betocrod.features.home.impl.widgets.HomeScaffold

@Composable
fun HomeScreen(
    onItemClick: (Song) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScaffold(
        modifier = modifier,
        onItemClick = onItemClick,
        homeState = viewModel.state
    )
}
