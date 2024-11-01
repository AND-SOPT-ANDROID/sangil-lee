package com.sopt.domain.usecase

import com.sopt.domain.exception.SignInError
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(email: String, password: String): Result<Unit> {
        return runCatching {
            when {
                email.isBlank() -> Result.failure(SignInError.EmailInputEmpty())
                password.isBlank() -> Result.failure(SignInError.PasswordInputEmpty())
                else -> userRepository.trySignIn(email, password)
            }
        }
    }
}