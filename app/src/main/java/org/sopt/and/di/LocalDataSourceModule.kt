package org.sopt.and.di

import android.content.SharedPreferences
import com.sopt.data.datasource.local.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.qualifer.User
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun provideUserLocalDataSource(
        @User userSharedPreferences: SharedPreferences
    ): UserLocalDataSource
}