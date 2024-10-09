package org.sopt.and.ui.screen.signin.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.component.textfield.FilledTextField

@Composable
fun SignInContentView(
    modifier: Modifier = Modifier,
    emailInput: String,
    passwordInput: String,
    onEmailInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        FilledTextField(
            value = emailInput,
            onValueChange = onEmailInputChanged
        )
        FilledTextField(
            modifier = Modifier.padding(top = 6.dp),
            value = passwordInput,
            onValueChange = onPasswordInputChanged
        )
    }
}