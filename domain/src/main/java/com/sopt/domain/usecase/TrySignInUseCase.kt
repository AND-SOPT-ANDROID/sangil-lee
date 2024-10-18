package com.sopt.domain.usecase

import com.sopt.domain.exception.SignInError
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class TrySignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(email: String, password: String): Result<Unit> {
        return runCatching {
            if (email.isBlank())
                Result.failure(SignInError.EmailInputEmpty())
            else if (password.isBlank())
                Result.failure(SignInError.PasswordInputEmpty())
            else
                userRepository.trySignIn(email, password)
        }
    }
}