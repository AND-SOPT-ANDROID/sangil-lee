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
        return runCatching {
            when {
                username.isBlank() -> Result.failure(SignUpError.UsernameInputEmpty())
                password.isBlank() -> Result.failure(SignUpError.PasswordInputEmpty())
                username.isValidUsername().not() -> Result.failure(SignUpError.InvalidUsername())
                password.isValidPassword().not() -> Result.failure(SignUpError.InvalidPassword())
                else -> userRepository.signUp(username, password, hobby)
            }
        }
    }
}