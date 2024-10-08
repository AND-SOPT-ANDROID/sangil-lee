package org.sopt.and.ui.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.component.text.TextFieldPlaceholder
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun FilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
    color: Color = WavveTheme.colorScheme.surfaceVariant,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = WavveTheme.typography.bodyMedium.copy(
        color = WavveTheme.colorScheme.primary
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(WavveTheme.colorScheme.accent),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(innerPadding),
        ) {
            TextFieldPlaceholder(text = if (value.isEmpty()) placeholder else "")
            innerTextField()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilledTextFieldPreview() {
    FilledTextField(
        value = "Hello, World!",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun FilledTextFieldNoTextPreview() {
    FilledTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}