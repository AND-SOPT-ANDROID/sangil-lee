package org.sopt.and.di

import android.content.Context
import android.content.SharedPreferences
import com.sopt.data.UserInjectParam
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalApiModule {

    @UserInjectParam
    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("user.pref", Context.MODE_PRIVATE)
    }
}