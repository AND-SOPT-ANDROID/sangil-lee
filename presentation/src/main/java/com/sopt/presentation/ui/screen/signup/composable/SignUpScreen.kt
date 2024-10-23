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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInUiState
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpUiState
import com.sopt.presentation.ui.screen.signup.viewmodel.SignUpViewModel
import com.sopt.presentation.ui.util.noRippleClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onActionIconClicked: () -> Unit = {},
    onSignUpSuccess: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val emailInput = viewModel.email.collectAsStateWithLifecycle()
    val passwordInput = viewModel.password.collectAsStateWithLifecycle()

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
        DefaultSurface(
            modifier = Modifier.padding(innerPadding)
        ) {
            SignUpContentScreen(
                modifier = Modifier.padding(horizontal = 12.dp),
                emailInput = emailInput.value,
                passwordInput = passwordInput.value,
                onEmailInputChanged = viewModel::onEmailInputChanged,
                onPasswordInputChanged = viewModel::onPasswordInputChanged,
                onSignUpButtonClicked = viewModel::signUp
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.signUpUiState.collect {
            var snackbarMessage = ""
            when (it) {
                is SignUpUiState.Success -> onSignUpSuccess()
                is SignUpUiState.EmailInputEmpty -> snackbarMessage =
                    ContextCompat.getString(context, R.string.require_email_input)

                is SignUpUiState.PasswordInputEmpty -> snackbarMessage =
                    ContextCompat.getString(context, R.string.require_password_input)

                is SignUpUiState.InvalidEmail -> snackbarMessage =
                    ContextCompat.getString(context, R.string.not_exist_email)

                is SignUpUiState.InvalidPassword -> snackbarMessage =
                    ContextCompat.getString(context, R.string.not_exist_password)
            }
            if (it !is SignUpUiState.Success)
                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(message = snackbarMessage)
                }
        }
    }
}