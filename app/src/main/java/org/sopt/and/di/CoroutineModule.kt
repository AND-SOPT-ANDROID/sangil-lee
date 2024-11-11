package org.sopt.and.di

import com.sopt.data.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @IODispatcher
    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}