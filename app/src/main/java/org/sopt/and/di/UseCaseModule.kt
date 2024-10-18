package org.sopt.and.di

import com.sopt.domain.repository.UserRepository
import com.sopt.domain.usecase.SignUpAccountUseCase
import com.sopt.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun bindTrySignInUseCase(userRepository: UserRepository): SignInUseCase {
        return SignInUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterAccountUseCase(userRepository: UserRepository): SignUpAccountUseCase {
        return SignUpAccountUseCase(userRepository)
    }
}