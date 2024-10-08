package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SignUpInputContentView(
            modifier = modifier.padding(top = 40.dp).padding(horizontal = 12.dp)
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