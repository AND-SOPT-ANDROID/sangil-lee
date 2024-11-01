package com.sopt.presentation.ui.screen.my.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.presentation.R
import com.sopt.presentation.User
import com.sopt.presentation.ui.component.box.NoContentAlertBox
import com.sopt.presentation.ui.component.icon.PrimaryIcon
import com.sopt.presentation.ui.component.image.CircularImage
import com.sopt.presentation.ui.component.surface.VariantSurface
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.component.text.SecondaryText
import com.sopt.presentation.ui.theme.WavveTheme

@Composable
fun MyContentScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        VariantSurface {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularImage(
                        modifier = Modifier.size(60.dp),
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = stringResource(R.string.profile_image),
                    )
                    PrimaryText(
                        modifier = Modifier.padding(start = 12.dp),
                        text = User.email,
                        style = WavveTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    PrimaryIcon(
                        modifier = Modifier.padding(end = 6.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = stringResource(R.string.notification_content_description)
                    )
                    PrimaryIcon(
                        modifier = Modifier.padding(start = 12.dp),
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(R.string.notification_content_description)
                    )
                }

                SecondaryText(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.promote_first_payment),
                    style = WavveTheme.typography.bodyLarge
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryText(
                        text = stringResource(R.string.pay),
                        style = WavveTheme.typography.bodyLarge
                    )
                    PrimaryIcon(
                        modifier = Modifier.padding(start = 4.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.profile_image),

                    )
                }
            }
        }

        VariantSurface(
            modifier = Modifier.padding(top = 1.dp).fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp, bottom = 20.dp)
            ) {
                SecondaryText(
                    text = stringResource(R.string.have_no_wavve_access),
                    style = WavveTheme.typography.bodyLarge
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryText(
                        text = stringResource(R.string.pay),
                        style = WavveTheme.typography.bodyLarge
                    )
                    PrimaryIcon(
                        modifier = Modifier.padding(start = 4.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.profile_image)
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            PrimaryText(
                text = stringResource(R.string.total_viewing_history),
                style = WavveTheme.typography.headSmall
            )
            NoContentAlertBox(
                modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp),
                text = stringResource(R.string.no_viewing_history)
            )

            PrimaryText(
                text = stringResource(R.string.interested_program),
                style = WavveTheme.typography.headSmall
            )
            NoContentAlertBox(
                modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp),
                text = stringResource(R.string.no_interested_program)
            )
        }
    }
}

@Composable
@Preview
private fun MyContentScreenPreview() {
    MyContentScreen()
}