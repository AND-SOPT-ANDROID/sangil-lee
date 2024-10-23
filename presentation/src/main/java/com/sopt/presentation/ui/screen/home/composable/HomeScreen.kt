package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.screen.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    DefaultSurface(
        modifier = modifier
            .statusBarsPadding()
    ) {
        HomeContentScreen(
            modifier = Modifier.padding(top = 4.dp),
            headVideoOverviews = viewModel.headVideoOverviews,
            commonVideoOverviews = viewModel.commonVideoOverviews,
            topVideoOverviews = viewModel.topVideoOverviews,
            onVideoTypeSelected = { TODO() },
        )
    }
}