package com.sopt.presentation.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R

@Composable
fun WavveLogoImage() {
    Image(
        modifier = Modifier.width(100.dp),
        painter = painterResource(id = R.drawable.logo_wavve),
        contentDescription = stringResource(R.string.wavve)
    )
}