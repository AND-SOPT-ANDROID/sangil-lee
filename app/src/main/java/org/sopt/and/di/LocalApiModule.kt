package org.sopt.and.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.and.qualifer.User
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalApiModule {

    @User
    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("user.pref", Context.MODE_PRIVATE)
    }
}