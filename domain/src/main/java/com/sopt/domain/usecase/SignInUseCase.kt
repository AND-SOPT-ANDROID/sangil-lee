package com.sopt.domain.usecase

import com.sopt.domain.exception.SignInError
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(username: String, password: String): Result<Unit> {
        return runCatching {
            when {
                username.isBlank() -> Result.failure(SignInError.UsernameInputEmpty())
                password.isBlank() -> Result.failure(SignInError.PasswordInputEmpty())
                else -> userRepository.signIn(username, password)
            }
        }
    }
}