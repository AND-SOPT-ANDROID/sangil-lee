package com.sopt.data.repository

import com.sopt.data.datasource.local.UserLocalDataSource
import com.sopt.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun saveAccount(email: String, password: String) {
        userLocalDataSource.saveAccount(email, password)
    }

    override fun trySignIn(email: String, password: String): Result<Unit> {
        return userLocalDataSource.trySignIn(email, password)
    }
}