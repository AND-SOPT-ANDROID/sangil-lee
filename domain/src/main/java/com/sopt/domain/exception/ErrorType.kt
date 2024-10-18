package com.sopt.domain.exception

sealed class SignInError : Throwable() {
    class NotExistEmail() : SignInError()
    class PasswordNotMatchingWithEmail() : SignInError()
    class EmailInputEmpty() : SignInError()
    class PasswordInputEmpty() : SignInError()
}

sealed class SignUpError : Throwable() {
    class InvalidEmail() : SignUpError()
    class InvalidPassword() : SignUpError()
    class EmailInputEmpty() : SignUpError()
    class PasswordInputEmpty() : SignUpError()
}