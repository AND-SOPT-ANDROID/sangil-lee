package org.sopt.and.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object WavveColorDarkTokens {
    val primary = Color.White
    val secondary = Gray20
    val accent = Blue10
    val background = Black10
    val onBackground = Color.White
}

class WavveColorScheme(
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val background: Color,
    val onBackground: Color
)

fun wavveDarkColorScheme(
    primary: Color = WavveColorDarkTokens.primary,
    secondary: Color = WavveColorDarkTokens.secondary,
    accent: Color = WavveColorDarkTokens.accent,
    background: Color = WavveColorDarkTokens.background,
    onBackground: Color = WavveColorDarkTokens.onBackground
): WavveColorScheme {
    return WavveColorScheme(
        primary = primary,
        secondary = secondary,
        accent = accent,
        background = background,
        onBackground = onBackground
    )
}

object WavveTheme {
    val colorScheme: WavveColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWavveColorScheme.current
}

internal val LocalWavveColorScheme = staticCompositionLocalOf { wavveDarkColorScheme() }