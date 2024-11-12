package com.sopt.presentation.ui.screen.search.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.component.text.PrimaryText
import com.sopt.presentation.ui.component.textfield.BottomLinedTextField
import com.sopt.presentation.ui.screen.search.viewmodel.SearchResultUiState
import com.sopt.presentation.ui.screen.search.viewmodel.SearchViewModel
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchedHobby by viewModel.searchedHobby.collectAsStateWithLifecycle()

    DefaultSurface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomLinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = searchQuery,
                color = WavveTheme.colorScheme.background,
                onValueChange = viewModel::onSearchQueryChanged,
                placeholder = stringResource(R.string.search_placeholder),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Number
                ), keyboardActions = KeyboardActions(
                    onSearch = { keyboardController?.hide() }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = WavveTheme.colorScheme.tertiary
                    )
                }, trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            modifier = Modifier
                                .size(16.dp)
                                .noRippleClickable {
                                    viewModel.onSearchQueryChanged("")
                                },
                            painter = painterResource(R.drawable.ic_clear),
                            contentDescription = null,
                            tint = WavveTheme.colorScheme.tertiary
                        )
                    }
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    searchedHobby is SearchResultUiState.Success -> {
                        val hobby = (searchedHobby as SearchResultUiState.Success).hobby
                        PrimaryText(text = "너의 취미는 $hobby",)
                    }
                    searchedHobby is SearchResultUiState.NotExistUser -> {
                        PrimaryText(text = "존재하지 않는 사용자입니다.",)
                    }
                }
            }
            SearchContentScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                displayedVideoOverviews = viewModel.displayedVideoOverviews
            )
        }
    }
}