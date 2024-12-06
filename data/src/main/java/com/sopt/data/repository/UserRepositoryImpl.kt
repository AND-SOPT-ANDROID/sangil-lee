package com.sopt.data.repository

import com.sopt.data.IODispatcher
import com.sopt.data.datasource.local.TokenLocalDataSource
import com.sopt.data.datasource.local.UserLocalDataSource
import com.sopt.data.datasource.remote.UserRemoteDataSource
import com.sopt.data.request.SignInRequest
import com.sopt.data.request.SignUpRequest
import com.sopt.data.request.UpdateProfileRequest
import com.sopt.domain.exception.SearchHobbyError
import com.sopt.domain.exception.SignInError
import com.sopt.domain.exception.SignUpError
import com.sopt.domain.exception.runCatchingExceptCancellation
import com.sopt.domain.model.Account
import com.sopt.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    private val _myHobby = MutableStateFlow("")
    private val myHobby: StateFlow<String> = flow {
        val token = tokenLocalDataSource.getToken()
        _myHobby.value = userRemoteDataSource.fetchMyHobby(token).hobby ?: ""

        emitAll(_myHobby)
    }.stateIn(
        scope = CoroutineScope(ioDispatcher),
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    override suspend fun signUp(username: String, password: String, hobby: String): Result<Unit> {
        return runCatchingExceptCancellation(SignUpError.AlreadyExistUsername()) {
            userRemoteDataSource.signUp(SignUpRequest(username, password, hobby))
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return runCatchingExceptCancellation(
            SignInError.NotExistUsername(),
            SignInError.PasswordNotMatchingWithUsername()
        ) {
            val signInDto = userRemoteDataSource.signIn(SignInRequest(username, password))
            signInDto.token?.let { tokenLocalDataSource.saveToken(it) }
        }
    }

    override fun fetchMyHobby(): Flow<String> {
        return myHobby
    }

    override suspend fun fetchUserHobby(no: Int): Result<String> {
        return runCatchingExceptCancellation(SearchHobbyError.NotExistUserNo()) {
            val token = tokenLocalDataSource.getToken()
            userRemoteDataSource.fetchUserHobby(token, no).hobby ?: ""
        }
    }

    override suspend fun updateProfile(password: String, hobby: String): Result<Unit> {
        return runCatchingExceptCancellation {
            val token = tokenLocalDataSource.getToken()
            userRemoteDataSource.updateProfile(token, UpdateProfileRequest(password, hobby)).also {
                _myHobby.value = hobby
            }
        }
    }

    override suspend fun saveAccount(account: Account): Result<Unit> {
        return runCatchingExceptCancellation {
            userLocalDataSource.saveAccount(account)
        }
    }

    override suspend fun getAccount(): Account? {
        return userLocalDataSource.getAccount()
    }
}
