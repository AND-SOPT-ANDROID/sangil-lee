package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.util.noRippleClickable

@Composable
fun HeadDisplayedVideoHorizontalPager(
    modifier: Modifier = Modifier,
    state: PagerState,
    videoOverviews: List<VideoOverviewViewState>,
    onVideoClicked: (VideoOverviewViewState) -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) { idx ->
        HeadDisplayedVideoItem(
            modifier = Modifier.fillMaxSize(),
            videoOverview = videoOverviews[idx], onClick = onVideoClicked,
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HeadDisplayedVideoItem(
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
            modifier = Modifier.fillMaxSize(),
            model = videoOverview.titleImage,
            contentDescription = videoOverview.title,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun HeadDisplayedVideoItemPreview() {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Image(
            imageVector = Icons.Filled.Lock,
            contentDescription = null
        )
    }
}