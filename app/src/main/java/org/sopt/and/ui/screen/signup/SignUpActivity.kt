package org.sopt.and.ui.screen.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import org.sopt.and.R
import org.sopt.and.ui.component.icon.PrimaryIcon
import org.sopt.and.ui.component.surface.WavveDefaultSurface
import org.sopt.and.ui.screen.signup.composable.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(stringResource(R.string.title_sign_up))
                            },
                            modifier = Modifier,
                            actions = {
                                PrimaryIcon(
                                    modifier = Modifier.size(28.dp).padding(end = 8.dp),
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = stringResource(R.string.close_content_description)
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    WavveDefaultSurface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        SignUpScreen(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            onSignUpComplete = { email, password ->
                                intent.putExtra("email", email)
                                intent.putExtra("password", password)
                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        )
                    }
                }
            }
        }
    }
}