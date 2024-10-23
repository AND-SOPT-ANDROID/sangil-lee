package com.sopt.presentation.ui.screen.signin.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignInError
import com.sopt.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val emailInput = savedStateHandle.getStateFlow(EMAIL, "")
    val passwordInput = savedStateHandle.getStateFlow(PASSWORD, "")

    private val _signInUiState =
        MutableSharedFlow<SignInUiState>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val signInUiState = _signInUiState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onEmailInputChanged(email: String) {
        savedStateHandle[EMAIL] = email
    }

    fun onPasswordInputChanged(password: String) {
        savedStateHandle[PASSWORD] = password
    }

    fun trySignIn() {
        signInUseCase(emailInput.value, passwordInput.value).onSuccess {
            _signInUiState.tryEmit(SignInUiState.Success)
        }.onFailure {
            when (it) {
                is SignInError.EmailInputEmpty -> _signInUiState.tryEmit(SignInUiState.EmailInputEmpty)
                is SignInError.PasswordInputEmpty -> _signInUiState.tryEmit(SignInUiState.PasswordInputEmpty)
                is SignInError.NotExistEmail -> _signInUiState.tryEmit(SignInUiState.NotExistEmail)
                is SignInError.PasswordNotMatchingWithEmail -> _signInUiState.tryEmit(SignInUiState.PasswordNotMatchingWithEmail)
            }
        }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}

sealed interface SignInUiState {
    data object Success : SignInUiState
    data object EmailInputEmpty : SignInUiState
    data object PasswordInputEmpty : SignInUiState
    data object NotExistEmail : SignInUiState
    data object PasswordNotMatchingWithEmail : SignInUiState
}