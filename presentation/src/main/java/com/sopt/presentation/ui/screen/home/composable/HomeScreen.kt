package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.screen.home.viewmodel.HomeViewModel
import com.sopt.presentation.ui.state.VideoOverviewViewState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToVideoDetail: (VideoOverviewViewState) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
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
            onVideoTypeSelected = {
                // TODO()
            }, onVideoSelected = onNavigateToVideoDetail,
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope
        )
    }
}