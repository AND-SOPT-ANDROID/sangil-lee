package com.sopt.presentation.ui.screen.signin.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInContentScreen(
    modifier: Modifier = Modifier,
    onSignInButtonClicked: () -> Unit,
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
            onSignInButtonClicked = onSignInButtonClicked,
            onNavigateToSignUp = onNavigateToSignUp
        )
    }
}