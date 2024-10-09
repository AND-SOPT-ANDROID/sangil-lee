package org.sopt.and.util

private val emailRegex by lazy {
    Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$")
}

private val passwordRegex by lazy {
    Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,20}$")
}

fun String.isValidEmail(): Boolean = emailRegex.matches(this)
fun String.isValidPassword(): Boolean = passwordRegex.matches(this)
