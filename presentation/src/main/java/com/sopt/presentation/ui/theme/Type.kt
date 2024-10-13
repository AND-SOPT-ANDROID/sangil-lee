package com.sopt.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

class WavveTypography(
    val headLarge: TextStyle,
    val headMedium: TextStyle,
    val headSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val captionLarge: TextStyle,
) {
    companion object {
        val Default = WavveTypography(
            headLarge = WavveTypeTokens.headLarge,
            headMedium = WavveTypeTokens.headMedium,
            headSmall = WavveTypeTokens.headSmall,
            bodyLarge = WavveTypeTokens.bodyLarge,
            bodyMedium = WavveTypeTokens.bodyMedium,
            bodySmall = WavveTypeTokens.bodySmall,
            captionLarge = WavveTypeTokens.captionLarge
        )
    }
}

internal val LocalWavveTypography = staticCompositionLocalOf { WavveTypography.Default }