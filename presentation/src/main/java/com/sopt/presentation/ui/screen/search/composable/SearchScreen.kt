package com.sopt.presentation.ui.screen.search.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.component.textfield.BottomLinedTextField
import com.sopt.presentation.ui.screen.search.viewmodel.SearchViewModel
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel()
) {

    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    DefaultSurface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomLinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                value = searchQuery.value,
                color = WavveTheme.colorScheme.background,
                onValueChange = viewModel::onSearchQueryChanged,
                placeholder = stringResource(R.string.search_placeholder),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = WavveTheme.colorScheme.tertiary
                    )
                }, trailingIcon = {
                    if (searchQuery.value.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.size(16.dp).noRippleClickable {
                                viewModel.onSearchQueryChanged("")
                            },
                            painter = painterResource(R.drawable.ic_clear),
                            contentDescription = null,
                            tint = WavveTheme.colorScheme.tertiary
                        )
                    }
                }
            )
        }
    }
}