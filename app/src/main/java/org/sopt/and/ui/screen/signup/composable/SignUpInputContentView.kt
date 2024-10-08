package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.divider.CenterContentHorizontalDivider
import org.sopt.and.ui.component.row.SocialIconsRow
import org.sopt.and.ui.component.text.IconFrontText
import org.sopt.and.ui.component.text.TertiaryText
import org.sopt.and.ui.component.text.WavvePrimaryText
import org.sopt.and.ui.component.text.WavveSecondaryText
import org.sopt.and.ui.component.textfield.FilledTextField
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun SignUpInputContentView(
    modifier: Modifier = Modifier,
    emailInput: String,
    passwordInput: String,
    onEmailInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.primary
                )
                ) {
                    append("이메일과 비밀번호")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.secondary
                )
                ) {
                    append("만으로\n")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.primary
                )
                ) {
                    append("Wavve를 즐길 수 ")
                }
                withStyle(style = SpanStyle(
                    color = WavveTheme.colorScheme.secondary
                )
                ) {
                    append("있어요!")
                }
            },
            style = WavveTheme.typography.headSmall,
            fontWeight = FontWeight.Medium
        )

        FilledTextField(
            modifier = Modifier.padding(top = 28.dp).fillMaxWidth(),
            value = emailInput,
            onValueChange = onEmailInputChanged,
            placeholder = stringResource(R.string.sign_up_email_input_placeholder)
        )

        IconFrontText(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.sign_up_caution_exact_email),
            painter = painterResource(R.drawable.ic_caution),
        )

        FilledTextField(
            modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
            value = passwordInput,
            onValueChange = onPasswordInputChanged,
            placeholder = stringResource(R.string.sign_up_password_input_placeholder),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingContent = {
                WavvePrimaryText(
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible },
                    text = if (isPasswordVisible) stringResource(R.string.hide_password_indicate) else stringResource(R.string.show_password_indicate)
                )
            }
        )

        IconFrontText(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.sign_up_caution_password, 8, 20),
            painter = painterResource(R.drawable.ic_caution),
        )

        CenterContentHorizontalDivider(
            modifier = Modifier.padding(top = 56.dp)
        ) {
            WavveSecondaryText(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.sign_up_with_other),
            )
        }

        SocialIconsRow(
            modifier = Modifier.padding(top = 32.dp),
            spacing = 12.dp
        )

        TertiaryText(
            modifier = Modifier.padding(top = 48.dp),
            text = stringResource(R.string.sign_up_alert_social)
        )
    }
}

@Preview
@Composable
private fun SignUpInputContentViewPreview() {
    SignUpScreen(
        modifier = Modifier.fillMaxSize()
    )
}