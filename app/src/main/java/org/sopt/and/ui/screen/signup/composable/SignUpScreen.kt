package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.button.FullWidthTextButton

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SignUpInputContentView(
            modifier = modifier.padding(top = 40.dp).padding(horizontal = 12.dp),
            emailInput = emailInput,
            passwordInput = passwordInput,
            onEmailInputChanged = { emailInput = it },
            onPasswordInputChanged = { passwordInput = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        FullWidthTextButton(
            text = stringResource(R.string.wavve_sign_up),
            enabled = false
        )
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpScreen()
}