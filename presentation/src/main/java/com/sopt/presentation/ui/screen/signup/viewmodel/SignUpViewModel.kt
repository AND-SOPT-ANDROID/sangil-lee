package com.sopt.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.usecase.SignUpAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpAccountUseCase: SignUpAccountUseCase
) : ViewModel() {

    val emailInput: StateFlow<String>
        field = MutableStateFlow("")
    val passwordInput: StateFlow<String>
        field = MutableStateFlow("")

    private val _signUpUiState =
        MutableSharedFlow<SignUpUiState>(extraBufferCapacity = 1)
    val signUpUiState = _signUpUiState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onEmailInputChanged(email: String) {
        emailInput.value = email
    }

    fun onPasswordInputChanged(password: String) {
        passwordInput.value = password
    }

    fun signUp() {
        signUpAccountUseCase(emailInput.value, passwordInput.value).onSuccess {
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
}

sealed interface SignUpUiState {
    data object Success : SignUpUiState
    data object EmailInputEmpty : SignUpUiState
    data object PasswordInputEmpty : SignUpUiState
    data object InvalidEmail : SignUpUiState
    data object InvalidPassword : SignUpUiState
}