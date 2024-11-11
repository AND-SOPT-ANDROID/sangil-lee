package com.sopt.domain.usecase

import com.sopt.domain.exception.SearchHobbyError
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class SearchHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(query: String): Result<String> {
        return when {
            query.isBlank() -> Result.failure(SearchHobbyError.InputEmpty())
            query.toIntOrNull() == null -> Result.failure(SearchHobbyError.InputNotNumber())
            else -> userRepository.fetchUserHobby(query.toInt())
        }
    }
}