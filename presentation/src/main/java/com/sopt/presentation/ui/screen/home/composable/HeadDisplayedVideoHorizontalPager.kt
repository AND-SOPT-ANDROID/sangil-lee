package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.component.text.TertiaryText
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme
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
            videoOverview = videoOverviews[idx % videoOverviews.size], onClick = onVideoClicked,
            totalPage = videoOverviews.size,
            currentPage = idx % videoOverviews.size + 1
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HeadDisplayedVideoItem(
    modifier: Modifier = Modifier,
    videoOverview: VideoOverviewViewState,
    totalPage: Int,
    currentPage: Int,
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

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(50))
                .height(IntrinsicSize.Min)
                .background(WavveTheme.colorScheme.background.copy(alpha = 0.9f))
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryText(
                text = currentPage.toString(),
                style = WavveTheme.typography.captionLarge
            )
            VerticalDivider(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                thickness = 1.dp,
                color = WavveTheme.colorScheme.secondary
            )
            TertiaryText(
                text = totalPage.toString(),
                style = WavveTheme.typography.captionLarge
            )
        }
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