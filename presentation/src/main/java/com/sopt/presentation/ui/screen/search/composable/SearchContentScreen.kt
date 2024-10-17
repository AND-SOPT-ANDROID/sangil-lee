package com.sopt.presentation.ui.screen.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.presentation.ui.component.chip.DefaultRoundedChip
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.R

@Composable
fun SearchContentScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val color = WavveTheme.colorScheme.primary
                for (item in listOf(
                    Triple(R.drawable.ic_series, R.string.series_content_description) {},
                    Triple(R.drawable.ic_film, R.string.movie_content_description) {}
                )) {
                    DefaultRoundedChip(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                        onClick = item.third,
                        backgroundColor = WavveTheme.colorScheme.background
                    ) {
                        Icon(
                            painter = painterResource(item.first),
                            contentDescription = null,
                            tint = color
                        )
                        PrimaryText(
                            modifier = Modifier.padding(start = 6.dp),
                            text = stringResource(item.second)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.see_more_content_description),
                            tint = color
                        )
                    }
                }
            }
        }
    }
}