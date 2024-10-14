package com.sopt.presentation.ui.screen.my.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.presentation.ui.component.surface.WavveDefaultSurface

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        WavveDefaultSurface {
            MyContentScreen(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            )
        }
    }
}