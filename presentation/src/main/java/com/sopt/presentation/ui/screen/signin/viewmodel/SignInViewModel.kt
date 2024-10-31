package com.sopt.presentation.ui.screen.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignInError
import com.sopt.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val emailInput: StateFlow<String>
        field = MutableStateFlow("")
    val passwordInput: StateFlow<String>
        field = MutableStateFlow("")

    private val _signInUiState =
        MutableSharedFlow<SignInUiState>(extraBufferCapacity = 1)
    val signInUiState = _signInUiState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onEmailInputChanged(email: String) {
        emailInput.value = email
    }

    fun onPasswordInputChanged(password: String) {
        passwordInput.value = password
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
}

sealed interface SignInUiState {
    data object Success : SignInUiState
    data object EmailInputEmpty : SignInUiState
    data object PasswordInputEmpty : SignInUiState
    data object NotExistEmail : SignInUiState
    data object PasswordNotMatchingWithEmail : SignInUiState
}