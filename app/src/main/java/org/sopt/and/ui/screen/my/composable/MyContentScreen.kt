package org.sopt.and.ui.screen.my.composable

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
import org.sopt.and.R
import org.sopt.and.User
import org.sopt.and.ui.component.icon.PrimaryIcon
import org.sopt.and.ui.component.image.CircularImage
import org.sopt.and.ui.component.surface.VariantSurface
import org.sopt.and.ui.component.text.PrimaryText
import org.sopt.and.ui.component.text.SecondaryText

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
                        text = User.email
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
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryText(
                        text = stringResource(R.string.pay),
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
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryText(
                        text = stringResource(R.string.pay),
                    )
                    PrimaryIcon(
                        modifier = Modifier.padding(start = 4.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.profile_image)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun MyContentScreenPreview() {
    MyContentScreen()
}