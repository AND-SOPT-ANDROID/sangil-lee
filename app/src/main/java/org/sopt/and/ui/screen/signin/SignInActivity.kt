package org.sopt.and.ui.screen.signin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.surface.WavveDefaultSurface
import org.sopt.and.ui.component.text.PrimaryText
import org.sopt.and.ui.screen.signin.composable.SignInContentScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                PrimaryText(stringResource(R.string.wavve))
                            }, navigationIcon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = stringResource(R.string.navigate_up_content_description)
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    WavveDefaultSurface {
                        SignInContentScreen(
                            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(horizontal = 14.dp)
                        )
                    }
                }
            }
        }
    }
}