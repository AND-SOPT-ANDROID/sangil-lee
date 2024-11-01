package com.sopt.presentation.ui.component.divider

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.text.SecondaryText
import com.sopt.presentation.ui.theme.WavveTheme

@Composable
fun CenterContentHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = WavveTheme.colorScheme.surfaceVariant,
    thickness: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = thickness
        )
        content()
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = thickness
        )
    }
}

@Composable
@Preview
private fun CenterContentHorizontalDividerPreview() {
    CenterContentHorizontalDivider {
        SecondaryText(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(R.string.sign_up_with_other),
        )
    }
}