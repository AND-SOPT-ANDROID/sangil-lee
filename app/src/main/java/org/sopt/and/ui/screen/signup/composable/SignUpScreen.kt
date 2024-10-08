package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    SignUpInputContentView(
        modifier = modifier.padding(top = 40.dp).padding(horizontal = 12.dp)
    )
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpScreen()
}