package com.sopt.domain.usecase

import com.sopt.domain.exception.SignUpError
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.util.isValidEmail
import com.sopt.domain.util.isValidPassword
import javax.inject.Inject

class SignUpAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String, password: String, hobby: String): Result<Unit> {
        return runCatching {
            when {
                email.isBlank() -> Result.failure(SignUpError.EmailInputEmpty())
                password.isBlank() -> Result.failure(SignUpError.PasswordInputEmpty())
                email.isValidEmail().not() -> Result.failure(SignUpError.InvalidEmail())
                password.isValidPassword().not() -> Result.failure(SignUpError.InvalidPassword())
                else -> userRepository.signUp(email, password, hobby)
            }
        }
    }
}