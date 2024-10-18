package com.sopt.presentation.ui.screen.signin.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.presentation.User

@Composable
fun SignInContentScreen(
    modifier: Modifier = Modifier,
    onLoginResult: (Boolean) -> Unit,
    onNavigateToSignUp: () -> Unit,
    emailInput: String,
    passwordInput: String,
    onEmailInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit
) {

    Column(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        SignInContentView(
            modifier = Modifier.padding(top = 36.dp),
            emailInput = emailInput,
            passwordInput = passwordInput,
            onEmailInputChanged = onEmailInputChanged,
            onPasswordInputChanged = onPasswordInputChanged,
            onLoginButtonClicked = {
                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty() && User.email == emailInput && User.password == passwordInput)
                    onLoginResult(true)
                else
                    onLoginResult(false)
            }, onNavigateToSignUp = onNavigateToSignUp
        )
    }
}