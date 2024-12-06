package com.sopt.presentation.ui.screen.signup.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.domain.util.isValidHobby
import com.sopt.domain.util.isValidPassword
import com.sopt.domain.util.isValidUsername
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

    val signUpButtonActivated by remember(
        usernameInput,
        passwordInput,
        hobbyInput
    ) {
        derivedStateOf {
            usernameInput.isValidUsername() && passwordInput.isValidPassword() && hobbyInput.isValidHobby()
        }
    }

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
            onClick = onSignUpButtonClicked
        )
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpContentScreen()
}