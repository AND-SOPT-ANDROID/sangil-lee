package org.sopt.and.ui.screen.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.and.ui.component.surface.WavveDefaultSurface
import org.sopt.and.ui.screen.my.composable.MyContentScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    WavveDefaultSurface {
                        MyContentScreen(
                            modifier = Modifier.fillMaxSize().padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}