package org.sopt.and.ui.screen.signin.composable

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.button.RoundedHorizontalButton
import org.sopt.and.ui.component.divider.CenterContentHorizontalDivider
import org.sopt.and.ui.component.row.SocialIconsRow
import org.sopt.and.ui.component.text.PrimaryText
import org.sopt.and.ui.component.text.SecondaryText
import org.sopt.and.ui.component.text.TertiaryText
import org.sopt.and.ui.component.textfield.FilledTextField
import org.sopt.and.ui.screen.signup.SignUpActivity
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.util.noRippleClickable

@Composable
fun SignInContentView(
    modifier: Modifier = Modifier,
    emailInput: String,
    passwordInput: String,
    onEmailInputChanged: (String) -> Unit,
    onPasswordInputChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
) {

    val context = LocalContext.current
    val signUpLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            onEmailInputChanged(data?.getStringExtra("email") ?: emailInput)
            onPasswordInputChanged(data?.getStringExtra("password") ?: passwordInput)
        }
    }

    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        FilledTextField(
            value = emailInput,
            innerPadding = PaddingValues(horizontal = 12.dp, vertical = 18.dp),
            onValueChange = onEmailInputChanged,
            placeholder = stringResource(R.string.email_address_or_id),
        )
        FilledTextField(
            modifier = Modifier.padding(top = 6.dp).fillMaxWidth(),
            innerPadding = PaddingValues(horizontal = 12.dp, vertical = 18.dp),
            value = passwordInput,
            onValueChange = onPasswordInputChanged,
            placeholder = stringResource(R.string.password),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingContent = {
                PrimaryText(
                    modifier = Modifier.noRippleClickable { isPasswordVisible = !isPasswordVisible },
                    text = if (isPasswordVisible) stringResource(R.string.hide_password_indicate) else stringResource(R.string.show_password_indicate)
                )
            }
        )
        RoundedHorizontalButton(
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            onClick = onLoginButtonClicked
        ) {
            PrimaryText(
                modifier = Modifier.padding(vertical = 6.dp),
                text = stringResource(R.string.sign_in)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SecondaryText(
                text = stringResource(R.string.find_id),
                style = WavveTheme.typography.bodySmall,
            )
            VerticalDivider(
                color = WavveTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 2.dp)
            )
            SecondaryText(
                text = stringResource(R.string.reset_password),
                style = WavveTheme.typography.bodySmall,
            )
            VerticalDivider(
                color = WavveTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 2.dp)
            )
            SecondaryText(
                text = stringResource(R.string.sign_up),
                style = WavveTheme.typography.bodySmall,
                modifier = Modifier.noRippleClickable {
                    signUpLauncher.launch(Intent(context, SignUpActivity::class.java))
                }
            )
        }

        CenterContentHorizontalDivider(
            modifier = Modifier.padding(top = 56.dp)
        ) {
            SecondaryText(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.sign_in_with_other),
            )
        }

        SocialIconsRow(
            modifier = Modifier.padding(top = 32.dp),
            spacing = 18.dp
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 48.dp)
        ) {
            TertiaryText(
                text = "· ",
                modifier = Modifier.alignByBaseline()
            )
            TertiaryText(
                modifier = Modifier.alignByBaseline(),
                text = stringResource(R.string.alert_social_sign),
                style = WavveTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun SignInContentViewPreview() {
    SignInContentView(
        emailInput = "",
        passwordInput = "",
        onEmailInputChanged = {},
        onPasswordInputChanged = {},
        onLoginButtonClicked = {}
    )
}