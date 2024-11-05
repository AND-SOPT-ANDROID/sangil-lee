package com.sopt.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.usecase.SignUpAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpAccountUseCase: SignUpAccountUseCase
) : ViewModel() {

    val emailInput: StateFlow<String>
        field = MutableStateFlow("")
    val passwordInput: StateFlow<String>
        field = MutableStateFlow("")
    val hobbyInput: StateFlow<String>
        field = MutableStateFlow("")

    val signUpUiState: SharedFlow<SignUpUiState>
        field = MutableSharedFlow<SignUpUiState>()

    fun onEmailInputChanged(email: String) {
        emailInput.value = email
    }

    fun onPasswordInputChanged(password: String) {
        passwordInput.value = password
    }

    fun onHobbyInputChanged(hobby: String) {
        hobbyInput.value = hobby
    }

    fun signUp() {
        viewModelScope.launch {
            signUpAccountUseCase(
                emailInput.value,
                passwordInput.value,
                hobbyInput.value
            ).onSuccess {
                signUpUiState.emit(SignUpUiState.Success)
            }.onFailure {
                when (it) {
                    is SignUpError.EmailInputEmpty -> signUpUiState.emit(SignUpUiState.EmailInputEmpty)
                    is SignUpError.PasswordInputEmpty -> signUpUiState.emit(SignUpUiState.PasswordInputEmpty)
                    is SignUpError.InvalidEmail -> signUpUiState.emit(SignUpUiState.InvalidEmail)
                    is SignUpError.InvalidPassword -> signUpUiState.emit(SignUpUiState.InvalidPassword)
                }
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