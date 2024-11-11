package com.sopt.presentation.ui.screen.my.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.exception.UpdateProfileError
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.usecase.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    private val _passwordInput = MutableStateFlow("")
    val passwordInput: StateFlow<String> = _passwordInput.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    private val _hobbyInput = MutableStateFlow("")
    val hobbyInput = flow {
        _hobbyInput.emit(userRepository.fetchMyHobby().first())
        emitAll(_hobbyInput)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    val updateProfileUiState: SharedFlow<UpdateProfileUiState>
        field = MutableSharedFlow()

    fun onPasswordInputChanged(password: String) {
        _passwordInput.value = password
    }

    fun onHobbyInputChanged(hobby: String) {
        _hobbyInput.value = hobby
    }

    fun updateProfile() {
        viewModelScope.launch {
            updateProfileUseCase(passwordInput.value, hobbyInput.value).onSuccess {
                println("dddddddd $it")
                updateProfileUiState.emit(UpdateProfileUiState.Success)
            }.onFailure {
                println("dddddddd2 $it")
                when(it) {
                    is UpdateProfileError.PasswordInputEmpty -> updateProfileUiState.emit(UpdateProfileUiState.PasswordInputEmpty)
                    is UpdateProfileError.HobbyInputEmpty -> updateProfileUiState.emit(UpdateProfileUiState.HobbyInputEmpty)
                    is UpdateProfileError.InvalidPassword -> updateProfileUiState.emit(UpdateProfileUiState.InvalidPassword)
                    is UpdateProfileError.InvalidHobby -> updateProfileUiState.emit(UpdateProfileUiState.InvalidHobby)
                }
            }
        }
    }
}

sealed interface UpdateProfileUiState {
    data object Success : UpdateProfileUiState
    data object PasswordInputEmpty : UpdateProfileUiState
    data object HobbyInputEmpty : UpdateProfileUiState
    data object InvalidPassword : UpdateProfileUiState
    data object InvalidHobby : UpdateProfileUiState
}
