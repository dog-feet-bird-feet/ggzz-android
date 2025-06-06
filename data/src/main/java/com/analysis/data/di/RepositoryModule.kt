package com.analysis.data.di

import com.analysis.data.repository.AnalysisRepositoryImpl
import com.analysis.data.repository.HistoryRepositoryImpl
import com.analysis.data.repository.LoginRepositoryImpl
import com.analysis.data.repository.PersonalityRepositoryImpl
import com.analysis.data.repository.UploadRepositoryImpl
import com.analysis.domain.repository.AnalysisRepository
import com.analysis.domain.repository.HistoryRepository
import com.analysis.domain.repository.LoginRepository
import com.analysis.domain.repository.PersonalityRepository
import com.analysis.domain.repository.UploadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindHistoryRepository(historyRepositoryImpl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    fun bindUploadRepository(uploadRepositoryImpl: UploadRepositoryImpl): UploadRepository

    @Binds
    fun bindAnalysisRepository(analysisRepositoryImpl: AnalysisRepositoryImpl): AnalysisRepository

    @Binds
    fun bindPersonalityAnalyzeRepository(personalityAnalyzeRepositoryImpl: PersonalityRepositoryImpl): PersonalityRepository

    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
