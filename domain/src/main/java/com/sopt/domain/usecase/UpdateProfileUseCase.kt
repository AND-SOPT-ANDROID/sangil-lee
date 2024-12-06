package com.sopt.domain.usecase

import com.sopt.domain.exception.UpdateProfileError
import com.sopt.domain.repository.UserRepository
import com.sopt.domain.util.isValidHobby
import com.sopt.domain.util.isValidPassword
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(password: String, hobby: String): Result<Unit> {
        return when {
            password.isBlank() -> Result.failure(UpdateProfileError.PasswordInputEmpty())
            hobby.isBlank() -> Result.failure(UpdateProfileError.HobbyInputEmpty())
            password.isValidPassword().not() -> Result.failure(UpdateProfileError.InvalidPassword())
            hobby.isValidHobby().not() -> Result.failure(UpdateProfileError.InvalidHobby())
            else -> userRepository.updateProfile(password, hobby)
        }
    }
}