package org.sopt.and.ui.screen.signup.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
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
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        modifier = Modifier.fillMaxSize()
    )
}