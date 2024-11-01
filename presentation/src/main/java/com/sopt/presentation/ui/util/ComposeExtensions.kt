package com.sopt.presentation.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = this.clickable(
    indication = null,
    interactionSource = remember { MutableInteractionSource() },
    onClick = onClick,
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role
)

@Composable
fun TextStyle.getMeasuredKoreanHeight(): Dp {
    val textMeasurer = rememberTextMeasurer()
    val koreanTextHeight = remember(this, textMeasurer) {
        textMeasurer.measure(
            text = "가",
            style = copy(textAlign = TextAlign.Center)
        ).size.height
    }
    return with(LocalDensity.current) { koreanTextHeight.toDp() }
}