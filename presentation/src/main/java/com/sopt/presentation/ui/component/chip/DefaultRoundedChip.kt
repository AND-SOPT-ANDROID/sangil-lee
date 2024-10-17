package com.sopt.presentation.ui.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.theme.WavveTheme

@Composable
fun DefaultRoundedChip(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    border: BorderStroke = BorderStroke(1.dp, WavveTheme.colorScheme.tertiary),
    backgroundColor: Color = WavveTheme.colorScheme.surface,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(50)
            ).border(
                border,
                shape = RoundedCornerShape(50)
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
@Preview
private fun DefaultRoundedChipPreview() {
    val color = WavveTheme.colorScheme.primary
    DefaultRoundedChip {
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = color
        )
        PrimaryText(
            modifier = Modifier.padding(start = 6.dp),
            text = "시리즈"
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = color
        )
    }
}