package com.sopt.presentation.ui.component.tab

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.theme.WavveTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DefaultTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    textStyle: TextStyle = WavveTheme.typography.bodyMedium,
    pagerState: PagerState = rememberPagerState { tabs.size },
    containerColor: Color = WavveTheme.colorScheme.background,
    indicatorColor: Color = WavveTheme.colorScheme.accent,
    indicatorThickness: Dp = 5.dp
) {

    val scope = rememberCoroutineScope()

    var indicatorWidths by remember {
        mutableStateOf(List(tabs.size) { 0 })
    }
    var indicatorPositions by remember {
        mutableStateOf(List(tabs.size) { Offset.Zero })
    }

    val animatedOffsetX by animateFloatAsState(
        targetValue = indicatorPositions[pagerState.currentPage].x,
        label = ""
    )

    val animatedWidth by animateDpAsState(
        targetValue = with(LocalDensity.current) { indicatorWidths[pagerState.currentPage].toDp() },
        label = ""
    )
    val indicatorThicknessPx = LocalDensity.current.run {
        indicatorThickness.toPx()
    }
    Column(
        modifier = modifier.background(containerColor),
    ) {
        Row(
            modifier = Modifier
        ) {
            tabs.forEachIndexed { idx, tab ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(idx)
                            }
                        }
                        .padding(vertical = 14.dp)
                ) {
                    PrimaryText(
                        modifier = Modifier.onGloballyPositioned { coordinate ->
                            indicatorWidths =
                                indicatorWidths.toMutableList()
                                    .also { it[idx] = coordinate.size.width }
                            indicatorPositions = indicatorPositions.toMutableList()
                                .also { it[idx] = coordinate.positionInRoot() }
                        },
                        text = tab,
                        style = textStyle
                    )
                }
            }
        }
        HorizontalDivider(
            color = indicatorColor,
            thickness = indicatorThickness,
            modifier = Modifier
                .width(animatedWidth)
                .offset {
                    IntOffset(
                        x = animatedOffsetX.roundToInt(),
                        y = -indicatorThicknessPx.roundToInt()
                    )
                }
        )
    }
}

@Composable
@Preview
private fun DefaultTabRowPreview() {
    DefaultTabRow(
        tabs = listOf("짧은 탭", "인기 영화", "엄청 긴탭탭탭탭"),
    )
}

@Composable
@Preview
private fun DefaultTabRowPreview2() {
    DefaultTabRow(
        tabs = listOf("짧은 탭", "인기 영화", "엄청 긴탭탭탭탭"),
        pagerState = rememberPagerState(initialPage = 2) { 3 }
    )
}