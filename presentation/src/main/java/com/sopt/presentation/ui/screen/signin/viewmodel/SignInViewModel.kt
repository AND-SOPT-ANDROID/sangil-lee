package com.sopt.presentation.ui.screen.signin.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val emailInput = savedStateHandle.getStateFlow(EMAIL, "")
    val passwordInput = savedStateHandle.getStateFlow(PASSWORD, "")

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