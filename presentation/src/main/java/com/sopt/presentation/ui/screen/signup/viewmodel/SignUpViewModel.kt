package com.sopt.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SignUpViewModel(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val email = savedStateHandle.getStateFlow(EMAIL, "")
    val password = savedStateHandle.getStateFlow(PASSWORD, "")

    fun onEmailInputChanged(email: String) {
        savedStateHandle[EMAIL] = email
    }

    fun onPasswordInputChanged(password: String) {
        savedStateHandle[PASSWORD] = password
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}