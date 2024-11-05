package com.sopt.presentation.ui.screen.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignInError
import com.sopt.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val usernameInput: StateFlow<String>
        field = MutableStateFlow("")
    val passwordInput: StateFlow<String>
        field = MutableStateFlow("")

    val signInUiState: SharedFlow<SignInUiState>
        field = MutableSharedFlow<SignInUiState>()

    fun onUsernameInputChanged(username: String) {
        usernameInput.value = username
    }

    fun onPasswordInputChanged(password: String) {
        passwordInput.value = password
    }

    fun trySignIn() {
        viewModelScope.launch {
            signInUseCase(usernameInput.value, passwordInput.value).onSuccess {
                signInUiState.emit(SignInUiState.Success)
            }.onFailure {
                when (it) {
                    is SignInError.UsernameInputEmpty -> signInUiState.emit(SignInUiState.UsernameInputEmpty)
                    is SignInError.PasswordInputEmpty -> signInUiState.emit(SignInUiState.PasswordInputEmpty)
                    is SignInError.NotExistUsername -> signInUiState.emit(SignInUiState.NotExistUsername)
                    is SignInError.PasswordNotMatchingWithUsername -> signInUiState.emit(
                        SignInUiState.PasswordNotMatchingWithUsername
                    )
                }
            }
        }
    }
}

sealed interface SignInUiState {
    data object Success : SignInUiState
    data object UsernameInputEmpty : SignInUiState
    data object PasswordInputEmpty : SignInUiState
    data object NotExistUsername : SignInUiState
    data object PasswordNotMatchingWithUsername : SignInUiState
}