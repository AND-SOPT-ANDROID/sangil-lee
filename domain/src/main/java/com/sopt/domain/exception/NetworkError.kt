package com.sopt.domain.exception

import java.io.IOException

data class NetworkError(
    val statusCode: Int,
    val errorCode: Int,
    override val message: String?
) : IOException()

abstract class CommonError : Throwable() {
    open val code: Int = -1
}

sealed class SignInError : CommonError() {
    class NotExistUsername : SignInError() {
        override val code: Int = 2
    }
    class PasswordNotMatchingWithUsername : SignInError() {
        override val code: Int = 1
    }
    class UsernameInputEmpty : SignInError()
    class PasswordInputEmpty : SignInError()
}

sealed class SignUpError : CommonError() {
    class InvalidUsername : SignUpError()
    class InvalidPassword : SignUpError()
    class InvalidHobby : SignUpError()
    class UsernameInputEmpty : SignUpError()
    class PasswordInputEmpty : SignUpError()
    class HobbyInputEmpty : SignUpError()
    class AlreadyExistUsername : SignUpError() {
        override val code: Int = 0
    }
}

sealed class SearchHobbyError : CommonError() {
    class InputEmpty : SearchHobbyError()
    class InputNotNumber : SearchHobbyError()
    class NotExistUserNo : SearchHobbyError() {
        override val code: Int = 1
    }
}

sealed class UpdateProfileError : CommonError() {
    class InvalidPassword : UpdateProfileError()
    class InvalidHobby : UpdateProfileError()
    class PasswordInputEmpty : UpdateProfileError()
    class HobbyInputEmpty : UpdateProfileError()
}