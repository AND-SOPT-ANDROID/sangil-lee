package com.sopt.presentation.ui.component.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.image.CircularImage

@Composable
fun SocialIconsRow(
    modifier: Modifier = Modifier,
    spacing: Dp = 12.dp,
    onKakaoClick: () -> Unit = {},
    onTClick: () -> Unit = {},
    onNaverClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onAppleClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.height(48.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterHorizontally)
    ) {
        CircularImage(
            modifier = Modifier.size(48.dp).clickable { onKakaoClick() },
            painter = painterResource(R.drawable.logo_kakao),
            contentDescription = stringResource(R.string.sign_up_with_kakao_content_description)
        )
        CircularImage(
            modifier = Modifier.size(48.dp).clickable { onTClick() },
            painter = painterResource(R.drawable.logo_t),
            contentDescription = stringResource(R.string.sign_up_with_t_content_description)
        )
        CircularImage(
            modifier = Modifier.size(48.dp).clickable { onNaverClick() },
            painter = painterResource(R.drawable.logo_naver),
            contentDescription = stringResource(R.string.sign_up_with_naver_content_description)
        )
        CircularImage(
            modifier = Modifier.size(48.dp).clickable { onFacebookClick() },
            painter = painterResource(R.drawable.logo_facebook),
            contentDescription = stringResource(R.string.sign_up_with_facebook_content_description)
        )
        CircularImage(
            modifier = Modifier.size(48.dp).clickable { onAppleClick() },
            painter = painterResource(R.drawable.logo_apple),
            contentDescription = stringResource(R.string.sign_up_with_apple_content_description)
        )
    }
}

@Composable
@Preview
private fun SocialIconsRowPreview() {
    SocialIconsRow(
        modifier = Modifier
    )
}