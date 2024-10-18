package com.sopt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.sopt.presentation.ui.navigation.WavveNavigation
import com.sopt.presentation.ui.theme.ANDANDROIDTheme
import com.sopt.presentation.ui.theme.Black10
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Black10.toArgb()
            )
        )
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                WavveNavigation(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
        }
    }
}