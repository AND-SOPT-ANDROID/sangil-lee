package com.sopt.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.usecase.SignUpAccountUseCase
import com.sopt.presentation.ui.screen.signin.viewmodel.SignInEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpAccountUseCase: SignUpAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    private val _effect = MutableSharedFlow<SignUpEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.UpdateUsername -> updateUsername(intent.username)
            is SignUpIntent.UpdatePassword -> updatePassword(intent.password)
            is SignUpIntent.UpdateHobby -> updateHobby(intent.hobby)
            SignUpIntent.TrySignUp -> signUp()
        }
    }

    private fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    private fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    private fun updateHobby(hobby: String) {
        _uiState.update { it.copy(hobby = hobby) }
    }

    private fun signUp() {
        viewModelScope.launch {
            val currentState = _uiState.value
            signUpAccountUseCase(
                currentState.username,
                currentState.password,
                currentState.hobby
            ).onSuccess {
                _uiState.update { it.copy(isLoading = false, signUpResult = SignUpResult.Success) }
            }.onFailure {
                val errorResult = when (it) {
                    is SignUpError.UsernameInputEmpty -> SignUpResult.UsernameInputEmpty
                    is SignUpError.PasswordInputEmpty -> SignUpResult.PasswordInputEmpty
                    is SignUpError.HobbyInputEmpty -> SignUpResult.HobbyInputEmpty
                    is SignUpError.InvalidUsername -> SignUpResult.InvalidUsername
                    is SignUpError.InvalidPassword -> SignUpResult.InvalidPassword
                    is SignUpError.InvalidHobby -> SignUpResult.InvalidHobby
                    is SignUpError.AlreadyExistUsername -> SignUpResult.AlreadyExistUsername
                    else -> null
                }
                _uiState.update { it.copy(isLoading = false, signUpResult = errorResult) }
            }
        }
    }
}

data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val hobby: String = "",
    val isLoading: Boolean = false,
    val signUpResult: SignUpResult? = null
)

sealed interface SignUpIntent {
    data class UpdateUsername(val username: String) : SignUpIntent
    data class UpdatePassword(val password: String) : SignUpIntent
    data class UpdateHobby(val hobby: String) : SignUpIntent
    data object TrySignUp : SignUpIntent
}

sealed interface SignUpResult {
    data object Success : SignUpResult
    data object UsernameInputEmpty : SignUpResult
    data object PasswordInputEmpty : SignUpResult
    data object HobbyInputEmpty : SignUpResult
    data object InvalidUsername : SignUpResult
    data object InvalidPassword : SignUpResult
    data object InvalidHobby : SignUpResult
    data object AlreadyExistUsername : SignUpResult
}

sealed interface SignUpEffect {
    data class ShowSnackbar(val message: String) : SignUpEffect
    data object Success : SignUpEffect
}