package com.sopt.presentation.ui.screen.signup.composable

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.icon.PrimaryIcon
import com.sopt.presentation.ui.component.snackbar.TextSnackbar
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.component.top.DefaultCenterAlignedTopAppBar
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpEffect
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpIntent
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpResult
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpUiState
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpViewModel
import com.sopt.presentation.ui.util.noRippleClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    onActionIconClicked: () -> Unit = {},
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultCenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.title_sign_up))
                },
                modifier = Modifier,
                actions = {
                    PrimaryIcon(
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                            .noRippleClickable(onClick = onActionIconClicked),
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = stringResource(R.string.close_content_description)
                    )
                }
            )
        }, snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .imePadding()
                    .padding(bottom = 40.dp),
                hostState = snackbarHostState
            ) {
                TextSnackbar(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    message = it.visuals.message
                )
            }
        }
    ) { innerPadding ->
        DefaultSurface(
            modifier = Modifier.padding(innerPadding)
        ) {
            SignUpContentScreen(
                modifier = Modifier.padding(horizontal = 12.dp),
                usernameInput = uiState.username,
                passwordInput = uiState.password,
                hobbyInput = uiState.hobby,
                onUsernameInputChanged = {
                    viewModel.handleIntent(SignUpIntent.UpdateUsername(it))
                },
                onPasswordInputChanged = {
                    viewModel.handleIntent(SignUpIntent.UpdatePassword(it))
                },
                onHobbyInputChanged = {
                    viewModel.handleIntent(SignUpIntent.UpdateHobby(it))
                },
                onSignUpButtonClicked = {
                    viewModel.handleIntent(SignUpIntent.TrySignUp)
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            var snackbarMessage = ""
            when (it) {
                is SignUpEffect.ShowSnackbar -> {
                    when(uiState.signUpResult) {
                        is SignUpResult.UsernameInputEmpty -> snackbarMessage =
                            ContextCompat.getString(context, R.string.require_username_input)

                        is SignUpResult.PasswordInputEmpty -> snackbarMessage =
                            ContextCompat.getString(context, R.string.require_password_input)

                        is SignUpResult.HobbyInputEmpty -> snackbarMessage =
                            ContextCompat.getString(context, R.string.require_hobby_input)

                        is SignUpResult.InvalidUsername -> snackbarMessage =
                            ContextCompat.getString(context, R.string.check_email_format)

                        is SignUpResult.InvalidPassword -> snackbarMessage =
                            ContextCompat.getString(context, R.string.check_password_format)

                        is SignUpResult.InvalidHobby -> snackbarMessage =
                            ContextCompat.getString(context, R.string.check_hobby_format)

                        is SignUpResult.AlreadyExistUsername -> snackbarMessage =
                            ContextCompat.getString(context, R.string.already_exist_username)

                        else -> Unit
                    }

                    if (uiState.signUpResult !is SignUpResult.Success)
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(message = snackbarMessage)
                        }
                }

                is SignUpEffect.Success -> {
                    onSignUpSuccess()
                    keyboardController?.hide()
                }
            }
        }
    }
}