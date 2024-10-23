package com.sopt.domain.usecase

import com.sopt.domain.exception.SignUpError
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.util.isValidEmail
import com.sopt.domain.util.isValidPassword
import javax.inject.Inject

class SignUpAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(email: String, password: String): Result<Unit> {
        return if (email.isBlank())
            Result.failure(SignUpError.EmailInputEmpty())
        else if (password.isBlank())
            Result.failure(SignUpError.PasswordInputEmpty())
        else if (email.isValidEmail().not())
            Result.failure(SignUpError.InvalidEmail())
        else if (password.isValidPassword().not())
            Result.failure(SignUpError.InvalidPassword())
        else
            Result.success(userRepository.saveAccount(email, password))
    }
}