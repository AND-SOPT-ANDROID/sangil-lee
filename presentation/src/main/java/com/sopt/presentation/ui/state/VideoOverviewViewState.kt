package com.sopt.presentation.ui.state

import androidx.compose.runtime.Immutable

@Immutable
data class VideoOverviewViewState(
    val id: Int,
    val title: String,
    val titleImage: String,
    val description: String,
)
