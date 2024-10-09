package org.sopt.and.ui.component.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun TextSnackbar(
    message: String,
    contentColor: Color = WavveTheme.colorScheme.primary,
    containerColor: Color = WavveTheme.colorScheme.surface,
    action: @Composable (() -> Unit)? = null,
    dismissAction: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    shape: Shape = SnackbarDefaults.shape,
    actionContentColor: Color = WavveTheme.colorScheme.accent,
    dismissActionContentColor: Color = WavveTheme.colorScheme.secondary
) {
    Snackbar(
        containerColor = containerColor,
        action = action,
        dismissAction = dismissAction,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        contentColor = contentColor,
        actionContentColor = actionContentColor,
        dismissActionContentColor = dismissActionContentColor
    ) {
        Text(text = message, color = contentColor)
    }
}

@Composable
@Preview
private fun TextSnackbarPreview() {
    TextSnackbar(message = "텍스트 스낵바")
}

@Composable
@Preview
private fun TextSnackbarActionDismissPreview() {
    TextSnackbar(
        message = "텍스트 스낵바",
        action = { Text("Action") },
        dismissAction = {
            Icon(
                modifier = Modifier.padding(horizontal = 12.dp),
                imageVector = Icons.Sharp.Close,
                contentDescription = "Close",
            )
        }
    )
}