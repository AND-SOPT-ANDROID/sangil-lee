package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.text.IconFrontText
import org.sopt.and.ui.component.textfield.FilledTextField
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {

    var emailInput by remember {
        mutableStateOf("")
    }
    var passwordInput by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.primary
                )) {
                    append("이메일과 비밀번호")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.secondary
                )) {
                    append("만으로\n")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.primary
                )) {
                    append("Wavve를 즐길 수 ")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.secondary
                )) {
                    append("있어요!")
                }
            },
            style = WavveTheme.typography.headSmall,
            fontWeight = FontWeight.Medium
        )

        FilledTextField(
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            value = emailInput,
            onValueChange = {
                emailInput = it
            },
            placeholder = stringResource(R.string.sign_up_email_input_placeholder)
        )

        IconFrontText(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.sign_up_caution_exact_email),
            painter = painterResource(R.drawable.ic_caution),
        )

        FilledTextField(
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            value = passwordInput,
            onValueChange = {
                passwordInput = it
            },
            placeholder = stringResource(R.string.sign_up_password_input_placeholder)
        )

        IconFrontText(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.sign_up_caution_password, 8, 20),
            painter = painterResource(R.drawable.ic_caution),
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        modifier = Modifier.fillMaxSize()
    )
}