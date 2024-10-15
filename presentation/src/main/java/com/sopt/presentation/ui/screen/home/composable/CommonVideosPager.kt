package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.icon.SecondaryIcon
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.state.CommonVideoOverviewsViewState
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable

@Composable
fun CommonVideosHorizontalPager(
    modifier: Modifier = Modifier,
    commonVideoOverview: CommonVideoOverviewsViewState,
    onVideoClicked: (VideoOverviewViewState) -> Unit
) {
    val state = rememberLazyListState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 32.dp

    Column(
        modifier = modifier
    ) {
        VideosKindDescriptionRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            kindDescription = commonVideoOverview.kindDescription
        )
        LazyRow(
            modifier = Modifier.padding(top = 6.dp),
            state = state,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            itemsIndexed(commonVideoOverview.videoOverviews) { idx, _ ->
                CommonVideoItem(
                    modifier = Modifier.width(screenWidth / 3f - 6.dp),
                    videoOverview = commonVideoOverview.videoOverviews[idx],
                    onClick = onVideoClicked
                )
            }
        }
    }
}

@Composable
private fun VideosKindDescriptionRow(
    modifier: Modifier = Modifier,
    kindDescription: String
) {
    Row(
        modifier = modifier,
    ) {
        PrimaryText(
            text = kindDescription,
            style = WavveTheme.typography.bodySuperLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        SecondaryIcon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.see_more_content_description)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CommonVideoItem(
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
            modifier = Modifier
                .fillMaxSize()
                .height(180.dp),
            model = videoOverview.titleImage,
            contentDescription = videoOverview.title,
            contentScale = ContentScale.Crop
        )
    }
}