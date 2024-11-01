package com.sopt.presentation.ui.component.top

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.presentation.ui.theme.WavveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCenterAlignedTopAppBar(
    title: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit) = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = WavveTheme.colorScheme.background,
        scrolledContainerColor = WavveTheme.colorScheme.background,
        titleContentColor = WavveTheme.colorScheme.onBackground,
        navigationIconContentColor = WavveTheme.colorScheme.onBackground,
        actionIconContentColor = WavveTheme.colorScheme.onBackground
    ),
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        expandedHeight = TopAppBarDefaults.TopAppBarExpandedHeight,
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = colors,
        scrollBehavior = null
    )
}