package com.analysis.data.di

import com.analysis.data.BuildConfig
import com.analysis.data.remote.api.AnalysisApiService
import com.analysis.data.remote.api.HistoryApiService
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
import retrofit2.Retrofit
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

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: GgzzInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
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
    fun provideHistoryApiService(retrofit: Retrofit): HistoryApiService = retrofit.create(HistoryApiService::class.java)

    @Provides
    @Singleton
    fun provideUploadApiService(retrofit: Retrofit): UploadApiService = retrofit.create(UploadApiService::class.java)

    @Provides
    @Singleton
    fun provideAnalysisApiService(retrofit: Retrofit): AnalysisApiService = retrofit.create(AnalysisApiService::class.java)
}
