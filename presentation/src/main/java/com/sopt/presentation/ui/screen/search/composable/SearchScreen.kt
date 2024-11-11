package com.sopt.presentation.ui.screen.search.composable

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.presentation.R
import com.sopt.presentation.ui.component.surface.DefaultSurface
import com.sopt.presentation.ui.component.textfield.BottomLinedTextField
import com.sopt.presentation.ui.screen.search.viewmodel.SearchResultUiState
import com.sopt.presentation.ui.screen.search.viewmodel.SearchViewModel
import com.sopt.presentation.ui.theme.WavveTheme
import com.sopt.presentation.ui.util.noRippleClickable
import kotlinx.coroutines.flow.collect

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchedHobby by viewModel.searchedHobby.collectAsStateWithLifecycle(SearchResultUiState.Nothing)

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
                    imeAction = ImeAction.Search
                ), keyboardActions = KeyboardActions(
                    onSearch = { viewModel.search(searchQuery) }
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

            when {
                searchedHobby is SearchResultUiState.Success -> {
                    val hobby = (searchedHobby as SearchResultUiState.Success).hobby
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "너의 취미는 $hobby",
                        )
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

    LaunchedEffect(Unit) {
        viewModel.searchedHobby.collect {
            when {
                it is SearchResultUiState.InputEmpty ->
                    Toast.makeText(context, context.getString(R.string.require_search_input), Toast.LENGTH_SHORT).show()
                it is SearchResultUiState.InputNotNumber ->
                    Toast.makeText(context, context.getString(R.string.require_only_number), Toast.LENGTH_SHORT).show()
                it is SearchResultUiState.NotExistUser ->
                    Toast.makeText(context, context.getString(R.string.not_exist_search_result), Toast.LENGTH_SHORT).show()
            }
        }
    }
}