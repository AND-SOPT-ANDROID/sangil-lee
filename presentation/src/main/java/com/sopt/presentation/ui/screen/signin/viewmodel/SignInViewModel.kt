package com.sopt.presentation.ui.screen.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.SignInError
import com.sopt.domain.model.Account
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.usecase.SignInUseCase
import com.sopt.domain.usecase.TryAutoSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tryAutoSignInUseCase: TryAutoSignInUseCase,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<SignInEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.UpdateUsername -> updateUsername(intent.username)
            is SignInIntent.UpdatePassword -> updatePassword(intent.password)
            is SignInIntent.TrySignIn -> trySignIn()
            SignInIntent.TryAutoSignIn -> tryAutoSignIn()
        }
    }

    private fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    private fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    private fun trySignIn() {
        viewModelScope.launch {
            val currentState = _uiState.value
            signInUseCase(currentState.username, currentState.password).onSuccess {
                userRepository.saveAccount(Account(currentState.username, currentState.password))
                _uiState.update { it.copy(isLoading = false, signInResult = SignInResult.Success) }
                _effect.emit(SignInEffect.Success)
            }.onFailure {
                val errorResult = when (it) {
                    is SignInError.UsernameInputEmpty -> SignInResult.UsernameInputEmpty
                    is SignInError.PasswordInputEmpty -> SignInResult.PasswordInputEmpty
                    is SignInError.NotExistUsername -> SignInResult.NotExistUsername
                    is SignInError.PasswordNotMatchingWithUsername -> SignInResult.PasswordNotMatchingWithUsername
                    else -> null
                }
                _uiState.update { it.copy(isLoading = false, signInResult = errorResult) }
                if (errorResult != null) {
                    _effect.emit(SignInEffect.ShowSnackbar("Error"))
                }
            }
        }
    }

    private fun tryAutoSignIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            tryAutoSignInUseCase().onSuccess {
                _uiState.update { it.copy(isLoading = false, signInResult = SignInResult.Success) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val signInResult: SignInResult? = null
)

sealed interface SignInIntent {
    data class UpdateUsername(val username: String) : SignInIntent
    data class UpdatePassword(val password: String) : SignInIntent
    data object TrySignIn : SignInIntent
    data object TryAutoSignIn : SignInIntent
}

sealed interface SignInResult {
    data object Success : SignInResult
    data object UsernameInputEmpty : SignInResult
    data object PasswordInputEmpty : SignInResult
    data object NotExistUsername : SignInResult
    data object PasswordNotMatchingWithUsername : SignInResult
}

sealed interface SignInEffect {
    data class ShowSnackbar(val message: String) : SignInEffect
    data object Success : SignInEffect
}