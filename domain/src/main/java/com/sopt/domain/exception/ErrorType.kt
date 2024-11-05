package com.sopt.domain.exception

sealed class SignInError : Throwable() {
    class NotExistUsername() : SignInError()
    class PasswordNotMatchingWithUsername() : SignInError()
    class UsernameInputEmpty() : SignInError()
    class PasswordInputEmpty() : SignInError()
}

sealed class SignUpError : Throwable() {
    class InvalidUsername() : SignUpError()
    class InvalidPassword() : SignUpError()
    class UsernameInputEmpty() : SignUpError()
    class PasswordInputEmpty() : SignUpError()
}