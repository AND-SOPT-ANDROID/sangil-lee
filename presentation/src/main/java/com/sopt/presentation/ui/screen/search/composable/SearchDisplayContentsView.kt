package com.sopt.presentation.ui.screen.search.composable

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme

@Composable
fun SearchDisplayContentsView(
    modifier: Modifier = Modifier,
    videoOverviews: List<VideoOverviewViewState>,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier.height(IntrinsicSize.Max).verticalScroll(scrollState)
    ) {
        videoOverviews.forEach {
            SearchDisplayContentsItem(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
                videoOverview = it
            )
            if (it != videoOverviews.last()) {
                HorizontalDivider(
                    modifier = Modifier.alpha(.35f),
                    color = WavveTheme.colorScheme.tertiary,
                    thickness = .5.dp
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SearchDisplayContentsItem(
    modifier: Modifier = Modifier,
    videoOverview: VideoOverviewViewState
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier.height(65.dp).width(120.dp).clip(
                shape = RoundedCornerShape(8.dp)
            ),
            model = videoOverview.titleImage,
            contentDescription = videoOverview.titleImage,
        )
        PrimaryText(
            modifier = Modifier.padding(start = 6.dp),
            text = videoOverview.title
        )
    }
}