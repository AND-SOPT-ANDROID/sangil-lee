package com.sopt.domain.usecase

import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class TryAutoSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        userRepository.getAccount()?.let { account ->
            return userRepository.signIn(account.username, account.password)
        } ?: run {
            return Result.failure(Exception("No account"))
        }
    }
}