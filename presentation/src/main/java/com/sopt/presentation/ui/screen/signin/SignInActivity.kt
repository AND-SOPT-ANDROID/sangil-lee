package com.sopt.presentation.ui.screen.signin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.sopt.presentation.ui.screen.signin.composable.SignInScreen
import com.sopt.presentation.ui.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignInScreen()
            }
        }
    }
}
