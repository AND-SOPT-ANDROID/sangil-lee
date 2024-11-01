package com.sopt.presentation.ui.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.text.TertiaryText
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.getMeasuredKoreanHeight
import com.sopt.presentation.R

@Composable
fun BottomLinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
    color: Color = WavveTheme.colorScheme.surfaceVariant,
    lineColor: Color = WavveTheme.colorScheme.primary,
    enabled: Boolean = true,
    textStyle: TextStyle = WavveTheme.typography.bodyLarge.copy(
        color = WavveTheme.colorScheme.primary
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(WavveTheme.colorScheme.accent),
    leadingIcon: @Composable (() -> Unit) = {},
    trailingIcon: @Composable (() -> Unit) = {},
) {
    val newHeight = textStyle.getMeasuredKoreanHeight()

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        cursorBrush = cursorBrush,
    ) { innerTextField ->
        Column(
            modifier = Modifier
                .background(color = color)
                .padding(contentPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon()
                Box(
                    modifier = Modifier.padding(start = 6.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    TertiaryText(
                        modifier = Modifier.height(newHeight),
                        text = if (value.isEmpty()) placeholder else "",
                        style = textStyle,
                        maxLines = 1
                    )
                    innerTextField()
                }
                Spacer(modifier = Modifier.weight(1f))
                trailingIcon()
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = lineColor,
                thickness = 1.dp
            )
        }
    }
}

@Composable
@Preview
private fun BottomLinedTextFieldPlaceholderPreview() {
    BottomLinedTextField(
        value = "",
        onValueChange = {},
        placeholder = "Placeholder",
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = WavveTheme.colorScheme.tertiary
            )
        }
    )
}

@Composable
@Preview
private fun BottomLinedTextFieldPreview() {
    BottomLinedTextField(
        value = "Hello Worrrrrrrrrrrld !",
        onValueChange = {},
        placeholder = "Placeholder",
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = WavveTheme.colorScheme.tertiary
            )
        }, trailingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_clear),
                contentDescription = null,
                tint = WavveTheme.colorScheme.tertiary
            )
        }
    )
}