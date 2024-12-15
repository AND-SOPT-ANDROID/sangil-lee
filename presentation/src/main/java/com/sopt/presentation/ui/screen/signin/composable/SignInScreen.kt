package com.sopt.presentation.ui.screen.signin.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.image.WavveLogoImage
import com.sopt.presentation.ui.component.snackbar.TextSnackbar
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.component.top.DefaultCenterAlignedTopAppBar
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInEffect
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInIntent
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInResult
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInUiState
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onNavigateToSignUp: () -> Unit,
    onSignInSuccess: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultCenterAlignedTopAppBar(
                title = {
                    WavveLogoImage()
                }, navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(32.dp),
                        imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.navigate_up_content_description)
                    )
                }
            )
        }, snackbarHost = {
            SnackbarHost(
                modifier = Modifier.imePadding(),
                hostState = snackbarHostState
            ) {
                TextSnackbar(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    message = it.visuals.message
                )
            }
        }
    ) { innerPadding ->
        DefaultSurface {
            SignInContentScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                onSignInButtonClicked = {
                    viewModel.handleIntent(SignInIntent.TrySignIn)
                },
                onNavigateToSignUp = onNavigateToSignUp,
                usernameInput = uiState.username,
                passwordInput = uiState.password,
                onUsernameInputChanged = {
                    viewModel.handleIntent(SignInIntent.UpdateUsername(it))
                },
                onPasswordInputChanged = {
                    viewModel.handleIntent(SignInIntent.UpdatePassword(it))
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            var snackbarMessage = ""
            when (it) {
                is SignInEffect.ShowSnackbar -> {
                    when (uiState.signInResult) {
                        SignInResult.UsernameInputEmpty -> snackbarMessage =
                            ContextCompat.getString(context, R.string.require_username_input)

                        SignInResult.PasswordInputEmpty -> snackbarMessage =
                            ContextCompat.getString(context, R.string.require_password_input)

                        SignInResult.NotExistUsername -> snackbarMessage =
                            ContextCompat.getString(context, R.string.not_exist_email)

                        SignInResult.PasswordNotMatchingWithUsername -> snackbarMessage =
                            ContextCompat.getString(context, R.string.not_exist_password)

                        else -> Unit
                    }
                    if (uiState.signInResult != SignInResult.Success)
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(message = snackbarMessage)
                        }
                }

                is SignInEffect.Success -> {
                    onSignInSuccess()
                    keyboardController?.hide()
                }
            }
        }
    }
}