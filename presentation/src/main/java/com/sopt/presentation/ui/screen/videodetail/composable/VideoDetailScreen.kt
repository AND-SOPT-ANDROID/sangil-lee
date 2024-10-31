package com.sopt.presentation.ui.screen.videodetail.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.ui.state.VideoOverviewViewState

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun SharedTransitionScope.VideoDetailScreen(
    modifier: Modifier = Modifier,
    videoOverview: VideoOverviewViewState,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        GlideImage(
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(videoOverview.id),
                    animatedVisibilityScope
                )
                .fillMaxWidth()
                .height(200.dp), model = videoOverview.titleImage,
            contentScale = ContentScale.Crop,
            contentDescription = videoOverview.title
        )
    }
}