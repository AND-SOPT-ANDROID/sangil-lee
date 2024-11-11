package com.sopt.data

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserSharedPref

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenEncryptedSharedPref

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcher