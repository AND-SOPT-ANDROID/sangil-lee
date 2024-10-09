package org.sopt.and.ui.component.box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.component.icon.SecondaryIcon
import org.sopt.and.ui.component.text.SecondaryText

@Composable
fun NoContentAlertBox(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SecondaryIcon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(R.drawable.ic_caution),
            contentDescription = stringResource(R.string.caution_content_description)
        )
        SecondaryText(
            modifier = Modifier.padding(top = 12.dp),
            text = text
        )
    }
}

@Composable
@Preview
private fun NoContentAlertBoxPreview() {
    NoContentAlertBox(text = "시청내역이 없어요")
}