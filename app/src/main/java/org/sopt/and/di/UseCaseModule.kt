package org.sopt.and.di

import com.sopt.domain.usecase.TrySignInUseCase
import dagger.Binds
import javax.inject.Singleton

abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindTrySignInUseCase(trySignInUseCase: TrySignInUseCase): TrySignInUseCase
}