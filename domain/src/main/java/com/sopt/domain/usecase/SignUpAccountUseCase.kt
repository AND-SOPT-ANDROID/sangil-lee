package com.sopt.domain.usecase

import com.sopt.domain.exception.SignUpError
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.util.isValidPassword
import com.sopt.domain.util.isValidUsername
import javax.inject.Inject

class SignUpAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String, password: String, hobby: String): Result<Unit> {
        return when {
            username.isBlank() -> Result.failure(SignUpError.UsernameInputEmpty())
            password.isBlank() -> Result.failure(SignUpError.PasswordInputEmpty())
            hobby.isBlank() -> Result.failure(SignUpError.HobbyInputEmpty())
            username.isValidUsername().not() -> Result.failure(SignUpError.InvalidUsername())
            password.isValidPassword().not() -> Result.failure(SignUpError.InvalidPassword())
            hobby.isValidUsername().not() -> Result.failure(SignUpError.InvalidHobby())
            else -> userRepository.signUp(username, password, hobby)
        }
    }
}