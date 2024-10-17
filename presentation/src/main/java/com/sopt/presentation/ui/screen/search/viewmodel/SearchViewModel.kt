package com.sopt.presentation.ui.screen.search.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SearchViewModel(
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
    
    companion object {
        const val SEARCH_QUERY = "searchQuery"
    }
}