package com.sopt.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.usecase.SignUpAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val signUpAccountUseCase: SignUpAccountUseCase
) : ViewModel() {

    val email = savedStateHandle.getStateFlow(EMAIL, "")
    val password = savedStateHandle.getStateFlow(PASSWORD, "")

    private val _signUpUiState =
        MutableSharedFlow<SignUpUiState>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val signUpUiState = _signUpUiState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onEmailInputChanged(email: String) {
        savedStateHandle[EMAIL] = email
    }

    fun onPasswordInputChanged(password: String) {
        savedStateHandle[PASSWORD] = password
    }

    fun signUp() {
        signUpAccountUseCase(email.value, password.value).onSuccess {
            _signUpUiState.tryEmit(SignUpUiState.Success)
        }.onFailure {
            when (it) {
                is SignUpError.EmailInputEmpty -> _signUpUiState.tryEmit(SignUpUiState.EmailInputEmpty)
                is SignUpError.PasswordInputEmpty -> _signUpUiState.tryEmit(SignUpUiState.PasswordInputEmpty)
                is SignUpError.InvalidEmail -> _signUpUiState.tryEmit(SignUpUiState.InvalidEmail)
                is SignUpError.InvalidPassword -> _signUpUiState.tryEmit(SignUpUiState.InvalidPassword)
            }
        }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}

sealed interface SignUpUiState {
    data object Success : SignUpUiState
    data object EmailInputEmpty : SignUpUiState
    data object PasswordInputEmpty : SignUpUiState
    data object InvalidEmail : SignUpUiState
    data object InvalidPassword : SignUpUiState
}