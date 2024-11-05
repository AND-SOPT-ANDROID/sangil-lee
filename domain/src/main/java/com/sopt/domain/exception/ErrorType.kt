package com.sopt.domain.exception

sealed class SignInError : Throwable() {
    class NotExistEmail() : SignInError()
    class PasswordNotMatchingWithEmail() : SignInError()
    class EmailInputEmpty() : SignInError()
    class PasswordInputEmpty() : SignInError()
}

sealed class SignUpError : Throwable() {
    class InvalidUsername() : SignUpError()
    class InvalidPassword() : SignUpError()
    class UsernameInputEmpty() : SignUpError()
    class PasswordInputEmpty() : SignUpError()
}