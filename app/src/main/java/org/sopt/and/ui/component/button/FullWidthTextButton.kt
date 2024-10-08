package org.sopt.and.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.component.text.WavvePrimaryText
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun FullWidthTextButton(
    modifier: Modifier = Modifier,
    color: ButtonColors = ButtonColors(
        containerColor = WavveTheme.colorScheme.accent,
        contentColor = WavveTheme.colorScheme.primary,
        disabledContainerColor = WavveTheme.colorScheme.surfaceBright,
        disabledContentColor = WavveTheme.colorScheme.primary
    ),
    text: String,
    onClick: () -> Unit = { },
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (enabled) color.containerColor else color.disabledContainerColor
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        WavvePrimaryText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = text,
            color = if (enabled) color.contentColor else color.disabledContentColor,
        )
    }
}

@Preview
@Composable
private fun FullWidthEnabledTextButtonPreview() {
    FullWidthTextButton(
        text = "Wavve 회원가입"
    )
}

@Preview
@Composable
private fun FullWidthDisabledTextButtonPreview() {
    FullWidthTextButton(
        text = "Wavve 회원가입",
        enabled = false
    )
}