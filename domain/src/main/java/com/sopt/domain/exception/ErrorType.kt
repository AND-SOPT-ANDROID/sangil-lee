package com.sopt.domain.exception

sealed class SignInError : Throwable() {
    class InvalidEmail() : SignInError()
    class InvalidPassword() : SignInError()
}