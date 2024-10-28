package com.sopt.presentation.ui.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Immutable
data class VideoOverviewViewState(
    val id: Int,
    val title: String,
    val titleImage: String,
    val description: String,
): Parcelable {

    companion object {
        val Empty = VideoOverviewViewState(
            id = 0,
            title = "",
            titleImage = "",
            description = "",
        )
    }
}
