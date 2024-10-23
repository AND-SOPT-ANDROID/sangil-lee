package com.sopt.presentation.ui.component.bottom

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable

enum class WavveBottomBarItem(
    val icon: ImageVector,
    @StringRes val titleResId: Int,
) {
    Home(Icons.Outlined.Home, R.string.title_home),
    Search(Icons.Outlined.Search, R.string.title_search),
    My(Icons.Outlined.AccountCircle, R.string.title_my),
}

@Composable
fun WavveBottomBar(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(6.dp),
    selectedTab: WavveBottomBarItem,
    onTabSelected: (WavveBottomBarItem) -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        WavveBottomBarItem.entries.forEach { item ->
            WavveBottomTabItem(
                modifier = Modifier.weight(1f),
                contentPadding = contentPadding,
                item = item,
                isSelected = item == selectedTab,
                onTabSelected = onTabSelected,
            )
        }
    }
}

@Composable
private fun WavveBottomTabItem(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    item: WavveBottomBarItem,
    isSelected: Boolean,
    onTabSelected: (WavveBottomBarItem) -> Unit,
) {
    Column(
        modifier = modifier
            .noRippleClickable(onClick = { onTabSelected(item) })
            .padding(vertical = contentPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = item.icon,
            contentDescription = stringResource(item.titleResId),
            tint = if (isSelected) WavveTheme.colorScheme.primary else WavveTheme.colorScheme.secondary,
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(item.titleResId),
            style = WavveTheme.typography.captionLarge,
            color = if (isSelected) WavveTheme.colorScheme.primary else WavveTheme.colorScheme.secondary,
        )
    }
}