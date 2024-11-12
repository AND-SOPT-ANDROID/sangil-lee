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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tryAutoSignInUseCase: TryAutoSignInUseCase,
    private val userRepository: UserRepository
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

    init {
        viewModelScope.launch {
            tryAutoSignInUseCase().onSuccess {
                signInUiState.emit(SignInUiState.Success)
            }
        }
    }
    
    fun trySignIn() {
        viewModelScope.launch {
            signInUseCase(usernameInput.value, passwordInput.value).onSuccess {
                signInUiState.emit(SignInUiState.Success)
                userRepository.saveAccount(Account(usernameInput.value, passwordInput.value))
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