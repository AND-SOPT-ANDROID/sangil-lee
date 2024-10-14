package com.sopt.presentation.ui.screen.home.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.text.SecondaryText
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.util.noRippleClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContentScreen(
    modifier: Modifier = Modifier,
    onVideoTypeSelected: (VideoType) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        stickyHeader {
            VideoTypeTabRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                onVideoTypeSelected = onVideoTypeSelected
            )
        }
    }
}

@Composable
fun VideoTypeTabRow(
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

enum class VideoType(
    @StringRes val titleResId: Int
) {
    NEW_CLASSIC(R.string.type_new_classic),
    DRAMA(R.string.type_drama),
    ENTERTAINMENT(R.string.type_entertainment),
    MOVIE(R.string.type_movie),
    ANIMATION(R.string.type_animation),
    ABROAD_SERIES(R.string.type_abroad_series),
    INFORMATION_CULTURE(R.string.type_information_culture),
    KIDS(R.string.type_kids),
    MOVIE_PLUS(R.string.type_movie_plus),
}