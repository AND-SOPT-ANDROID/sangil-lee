package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.presentation.ui.component.surface.WavveDefaultSurface
import com.sopt.presentation.ui.screen.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    WavveDefaultSurface(
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