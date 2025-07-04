package com.analysis.data.di

import com.analysis.data.BuildConfig
import com.analysis.data.remote.api.AnalysisApiService
import com.analysis.data.remote.api.HistoryApiService
import com.analysis.data.remote.api.LoginApiService
import com.analysis.data.remote.api.PersonalityApiService
import com.analysis.data.remote.api.UploadApiService
import com.analysis.data.remote.interceptor.GgzzInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @NoAuthClient
    @Singleton
    fun provideNoAuthOkHttpClient(interceptor: GgzzInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @AuthClient
    @Singleton
    fun provideOkHttpClient(interceptor: GgzzInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @NoAuthClient
    @Singleton
    fun provideRetrofitWithoutAuth(
        @NoAuthClient okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @AuthClient
    @Singleton
    fun provideRetrofit(
        @AuthClient okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHistoryApiService(@AuthClient retrofit: Retrofit): HistoryApiService = retrofit.create(HistoryApiService::class.java)

    @Provides
    @Singleton
    fun provideUploadApiService(@AuthClient retrofit: Retrofit): UploadApiService = retrofit.create(UploadApiService::class.java)

    @Provides
    @Singleton
    fun provideAnalysisApiService(@AuthClient retrofit: Retrofit): AnalysisApiService = retrofit.create(AnalysisApiService::class.java)

    @Provides
    @Singleton
    fun providePersonalityAnalyzeApiService(@AuthClient retrofit: Retrofit): PersonalityApiService = retrofit.create(
        PersonalityApiService::class.java,
    )

    @Provides
    @Singleton
    fun provideLoginApiService(@NoAuthClient retrofit: Retrofit): LoginApiService = retrofit.create(LoginApiService::class.java)
}
