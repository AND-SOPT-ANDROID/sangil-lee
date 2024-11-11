package com.sopt.domain.exception

import java.io.IOException

data class NetworkError(
    val statusCode: Int,
    val errorCode: Int,
    override val message: String?
) : IOException()

sealed class SignInError : Throwable() {
    class NotExistUsername() : SignInError()
    class PasswordNotMatchingWithUsername() : SignInError()
    class UsernameInputEmpty() : SignInError()
    class PasswordInputEmpty() : SignInError()
}

sealed class SignUpError : Throwable() {
    class InvalidUsername() : SignUpError()
    class InvalidPassword() : SignUpError()
    class InvalidHobby() : SignUpError()
    class UsernameInputEmpty() : SignUpError()
    class PasswordInputEmpty() : SignUpError()
    class HobbyInputEmpty() : SignUpError()
    class AlreadyExistUsername() : SignUpError()
}

sealed class SearchHobbyError : Throwable() {
    class InputEmpty : SearchHobbyError()
    class InputNotNumber : SearchHobbyError()
    class NotExistUserNo : SearchHobbyError()
}