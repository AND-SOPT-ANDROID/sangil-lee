package org.sopt.and.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object WavveColorDarkTokens {
    val primary = Color.White
    val secondary = Gray20
    val tertiary = Gray30
    val accent = Blue10
    val background = Black10
    val onBackground = Color.White
    val surface = Black20
    val surfaceVariant = Black30
}

class WavveColorScheme(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val accent: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val surfaceVariant: Color
)

fun wavveDarkColorScheme(
    primary: Color = WavveColorDarkTokens.primary,
    secondary: Color = WavveColorDarkTokens.secondary,
    tertiary: Color = WavveColorDarkTokens.tertiary,
    accent: Color = WavveColorDarkTokens.accent,
    background: Color = WavveColorDarkTokens.background,
    onBackground: Color = WavveColorDarkTokens.onBackground,
    surface: Color = WavveColorDarkTokens.surface,
    surfaceVariant: Color = WavveColorDarkTokens.surfaceVariant
): WavveColorScheme {
    return WavveColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        accent = accent,
        background = background,
        onBackground = onBackground,
        surface = surface,
        surfaceVariant = surfaceVariant
    )
}

object WavveTheme {
    val colorScheme: WavveColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWavveColorScheme.current
}

internal val LocalWavveColorScheme = staticCompositionLocalOf { wavveDarkColorScheme() }