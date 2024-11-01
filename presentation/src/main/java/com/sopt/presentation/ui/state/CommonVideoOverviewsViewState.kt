package com.sopt.presentation.ui.state

import androidx.compose.runtime.Immutable

@Immutable
data class CommonVideoOverviewsViewState(
    val kindDescription: String,
    val videoOverviews: List<VideoOverviewViewState>
)
