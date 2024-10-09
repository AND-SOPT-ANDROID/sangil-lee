package org.sopt.and.ui.screen.signin.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInContentScreen(
    modifier: Modifier = Modifier
) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        SignInContentView(
            modifier = Modifier.padding(top = 36.dp),
            emailInput = emailInput,
            passwordInput = passwordInput,
            onEmailInputChanged = { emailInput = it },
            onPasswordInputChanged = { passwordInput = it },
            onLoginButtonClicked = {
                // TODO : Implement login button click event
            }
        )
    }
}