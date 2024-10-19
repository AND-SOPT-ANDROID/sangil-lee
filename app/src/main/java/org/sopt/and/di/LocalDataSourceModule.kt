package org.sopt.and.di

import android.content.SharedPreferences
import com.sopt.data.datasource.local.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.qualifer.User
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        @User userSharedPreferences: SharedPreferences
    ): UserLocalDataSource {
        return UserLocalDataSource(userSharedPreferences)
    }
}