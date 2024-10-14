package com.sopt.presentation.ui.screen.signup.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.button.FullWidthTextButton
import com.sopt.presentation.util.isValidEmail
import com.sopt.presentation.util.isValidPassword

@Composable
fun SignUpContentScreen(
    modifier: Modifier = Modifier,
    emailInput: String = "",
    passwordInput: String = "",
    onEmailInputChanged: (String) -> Unit = {},
    onPasswordInputChanged: (String) -> Unit = {},
    onSignUpComplete: () -> Unit = { }
) {

    val context = LocalContext.current

    var signUpButtonEnabled by remember { mutableStateOf(false) }

    var signUpFailureMessage by remember { mutableStateOf("") }
    var toast = Toast.makeText(context, signUpFailureMessage, Toast.LENGTH_SHORT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(bottom = 24.dp)
        ) {
            SignUpInputContentView(
                modifier = modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 6.dp),
                emailInput = emailInput,
                passwordInput = passwordInput,
                onEmailInputChanged = onEmailInputChanged,
                onPasswordInputChanged = onPasswordInputChanged
            )
        }
        FullWidthTextButton(
            modifier = Modifier,
            text = stringResource(R.string.wavve_sign_up),
            activated = signUpButtonEnabled,
            onClick = {
                if (signUpButtonEnabled)
                    onSignUpComplete()
                else {
                    when {
                        emailInput.isValidEmail().not() -> {
                            toast.show()
                        }
                        passwordInput.isValidPassword().not() -> {
                            toast.show()
                        }
                    }
                }
            }
        )
    }

    LaunchedEffect(key1 = emailInput, key2 = passwordInput) {
        signUpButtonEnabled = emailInput.isValidEmail() && passwordInput.isValidPassword()
        when {
            emailInput.isValidEmail().not() -> {
                signUpFailureMessage = ContextCompat.getString(context, R.string.check_email_format)
            }
            passwordInput.isValidPassword().not() -> {
                signUpFailureMessage = ContextCompat.getString(context, R.string.check_password_format)
            }
        }
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpContentScreen()
}