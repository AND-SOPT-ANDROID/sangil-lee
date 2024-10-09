package org.sopt.and.ui.screen.my.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.and.ui.component.image.CircularImage
import org.sopt.and.ui.component.surface.VariantSurface
import org.sopt.and.R

@Composable
fun MyContentScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        VariantSurface {
            Column {
                Row {
                    CircularImage(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = stringResource(R.string.profile_image),
                    )
                }
            }
        }
    }
}