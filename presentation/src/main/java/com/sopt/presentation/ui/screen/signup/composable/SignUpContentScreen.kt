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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sopt.domain.util.isValidUsername
import com.sopt.domain.util.isValidHobby
import com.sopt.domain.util.isValidPassword
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.button.FullWidthTextButton

@Composable
fun SignUpContentScreen(
    modifier: Modifier = Modifier,
    usernameInput: String = "",
    passwordInput: String = "",
    hobbyInput: String = "",
    onUsernameInputChanged: (String) -> Unit = {},
    onPasswordInputChanged: (String) -> Unit = {},
    onHobbyInputChanged: (String) -> Unit = {},
    onSignUpButtonClicked: () -> Unit = { }
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val signUpButtonActivated by remember(
        usernameInput,
        passwordInput
    ) {
        derivedStateOf {
            usernameInput.isValidUsername() && passwordInput.isValidPassword() && hobbyInput.isValidHobby()
        }
    }

    var signUpFailureMessage by remember { mutableStateOf("") }
    val toast = Toast.makeText(context, signUpFailureMessage, Toast.LENGTH_SHORT)

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
                usernameInput = usernameInput,
                passwordInput = passwordInput,
                hobbyInput = hobbyInput,
                onUsernameInputChanged = onUsernameInputChanged,
                onPasswordInputChanged = onPasswordInputChanged,
                onHobbyInputChanged = onHobbyInputChanged
            )
        }
        FullWidthTextButton(
            modifier = Modifier,
            text = stringResource(R.string.wavve_sign_up),
            activated = signUpButtonActivated,
            onClick = {
                if (signUpButtonActivated) {
                    keyboardController?.hide()
                    onSignUpButtonClicked()
                } else {
                    when {
                        usernameInput.isValidUsername().not() -> toast.show()
                        passwordInput.isValidPassword().not() -> toast.show()
                    }
                }
            }
        )
    }

    LaunchedEffect(signUpButtonActivated) {
        when {
            usernameInput.isValidUsername().not() -> {
                signUpFailureMessage = ContextCompat.getString(context, R.string.check_email_format)
            }

            passwordInput.isValidPassword().not() -> {
                signUpFailureMessage =
                    ContextCompat.getString(context, R.string.check_password_format)
            }
        }
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpContentScreen()
}