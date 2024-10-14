package com.sopt.presentation.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.presentation.ui.component.image.WavveLogoImage
import com.sopt.presentation.ui.component.surface.WavveDefaultSurface
import com.sopt.presentation.ui.component.top.DefaultTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopAppBar(
                title = {
                    WavveLogoImage()
                }
            )
        }
    ) { innerPadding ->
        WavveDefaultSurface(
            modifier = Modifier.padding(innerPadding)
        ) {
            
        }
    }
}