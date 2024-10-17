package com.sopt.presentation.ui.component.tab

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.theme.WavveTheme
import kotlinx.coroutines.launch

@Composable
fun DefaultTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
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
                        text = tab
                    )
                }
            }
        }
        HorizontalDivider(
            color = indicatorColor,
            thickness = indicatorThickness,
            modifier = Modifier
                .width(with(LocalDensity.current) { indicatorWidths[pagerState.currentPage].toDp() })
                .offset(
                    x = with(LocalDensity.current) { indicatorPositions[pagerState.currentPage].x.toDp() },
                    y = -indicatorThickness
                )
        )
    }
}