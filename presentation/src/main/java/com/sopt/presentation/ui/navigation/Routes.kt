package com.sopt.presentation.ui.navigation

import kotlinx.serialization.Serializable

object Routes {

    object Auth {
        @Serializable object Graph
        @Serializable object SignIn
        @Serializable object SignUp
    }

    object Main {
        @Serializable object Graph
        @Serializable object Home
        @Serializable object Search
        @Serializable object My
    }
}