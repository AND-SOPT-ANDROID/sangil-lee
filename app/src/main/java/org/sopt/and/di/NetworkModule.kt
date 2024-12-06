package org.sopt.and.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.domain.exception.NetworkError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.and.BuildConfig
import org.sopt.and.adapter.ResultConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClient(
        responseInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.addInterceptor(responseInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideResponseInterceptor(
    ) : Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        if (response.isSuccessful.not()) {
            val errorBody = response.body?.string()
            val errorResponse = try {
                Json.decodeFromString<NetworkErrorResponse>(errorBody ?: "")
            } catch (e: Exception) {
                null
            }

            throw NetworkError(
                statusCode = response.code,
                errorCode = errorResponse?.code?.toInt() ?: -1,
                message = response.message,
            )
        }
        return@Interceptor response
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(ResultConverterFactory())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

@Serializable
private data class NetworkErrorResponse(
    val code: String
)