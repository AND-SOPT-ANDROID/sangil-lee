package org.sopt.and.util

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$")
    return emailRegex.matches(this)
}

fun String.isValidPassword(): Boolean {
    val passwordRegex = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,20}$")
    return passwordRegex.matches(this)
}