package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.state.CommonVideoOverviewsViewState
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.util.noRippleClickable

@Composable
fun TodayTopVideosPager(
    modifier: Modifier = Modifier,
    commonVideoOverview: CommonVideoOverviewsViewState,
    onVideoClicked: (VideoOverviewViewState) -> Unit
) {
    val state = rememberLazyListState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 32.dp

    Column(
        modifier = modifier
    ) {
        PrimaryText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = commonVideoOverview.kindDescription,
            style = WavveTheme.typography.bodySuperLarge,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier.padding(top = 6.dp),
            state = state,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state, snapPosition = SnapPosition.Start)
        ) {
            itemsIndexed(commonVideoOverview.videoOverviews) { idx, _ ->
                TodayTopVideoItem(
                    modifier = Modifier.width(screenWidth / 2f - 6.dp),
                    videoOverview = commonVideoOverview.videoOverviews[idx],
                    rank = idx + 1,
                    onClick = onVideoClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TodayTopVideoItem(
    modifier: Modifier = Modifier,
    videoOverview: VideoOverviewViewState,
    rank: Int,
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
            modifier = Modifier.fillMaxSize().height(320.dp).padding(bottom = 36.dp),
            model = videoOverview.titleImage,
            contentDescription = videoOverview.title,
            contentScale = ContentScale.Crop
        )

        PrimaryText(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 6.dp),
            text = "$rank",
            style = WavveTheme.typography.headSuperLarge,
        )
    }
}