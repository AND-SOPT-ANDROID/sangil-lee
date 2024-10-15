package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.util.noRippleClickable

@Composable
fun TodayTopVideosPager() {

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TodayTopVideoItem(
    modifier: Modifier = Modifier,
    videoOverview: VideoOverviewViewState,
    onClick: (VideoOverviewViewState) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .noRippleClickable { onClick(videoOverview) }
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize().height(320.dp),
            model = videoOverview.titleImage,
            contentDescription = videoOverview.title,
            contentScale = ContentScale.Crop
        )
    }
}