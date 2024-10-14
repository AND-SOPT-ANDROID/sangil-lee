package com.sopt.presentation.ui.screen.home.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.text.SecondaryText
import com.sopt.presentation.ui.screen.home.type.VideoType
import com.sopt.presentation.ui.state.VideoOverviewViewState
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.util.noRippleClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContentScreen(
    modifier: Modifier = Modifier,
    headVideoOverviews: List<VideoOverviewViewState>,
    onVideoTypeSelected: (VideoType) -> Unit,
    headDisplayedVideoCount: Int = 3
) {

    val headDisplayPagerState = rememberPagerState { headDisplayedVideoCount }

    LazyColumn(
        modifier = modifier,
    ) {
        stickyHeader {
            VideoTypeTabRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                onVideoTypeSelected = onVideoTypeSelected
            )
        }

        item {
            HeadDisplayedVideoHorizontalPager(
                modifier = Modifier.fillMaxWidth().height(500.dp).padding(top = 16.dp),
                state = headDisplayPagerState,
                videoOverviews = headVideoOverviews,
                onVideoClicked = { }
            )
        }
    }
}

@Composable
private fun VideoTypeTabRow(
    modifier: Modifier = Modifier,
    onVideoTypeSelected: (VideoType) -> Unit
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        VideoType.entries.forEach {
            SecondaryText(
                modifier = Modifier.noRippleClickable(onClick = { onVideoTypeSelected(it) }),
                text = stringResource(it.titleResId),
                style = WavveTheme.typography.bodyLarge
            )
        }
    }
}
