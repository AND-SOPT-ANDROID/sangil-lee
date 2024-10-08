package org.sopt.and.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object WavveTheme {
    val colorScheme: WavveColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWavveColorScheme.current
}