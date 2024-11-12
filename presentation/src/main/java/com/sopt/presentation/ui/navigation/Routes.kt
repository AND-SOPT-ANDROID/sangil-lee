package com.sopt.presentation.ui.navigation

import com.sopt.presentation.ui.state.VideoOverviewViewState
import kotlinx.serialization.Serializable

object Routes {

    object Auth {
        @Serializable
        object Graph
        @Serializable
        object SignIn
        @Serializable
        object SignUp
    }

    object Main {
        @Serializable
        object Graph
        @Serializable
        object Home
        @Serializable
        data object Search
        @Serializable
        object My
    }

    object VideoDetail {
        @Serializable
        object Graph
        @Serializable
        data class Video(val videoOverviewViewState: VideoOverviewViewState)
    }

    object MySetting {
        @Serializable
        object Graph
        @Serializable
        object EditProfile
    }
}