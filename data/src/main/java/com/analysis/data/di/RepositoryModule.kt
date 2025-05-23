package com.analysis.data.di

import com.analysis.data.repository.AnalysisRepositoryImpl
import com.analysis.data.repository.HistoryRepositoryImpl
import com.analysis.data.repository.PersonalityAnalyzeRepositoryImpl
import com.analysis.data.repository.UploadRepositoryImpl
import com.analysis.domain.repository.AnalysisRepository
import com.analysis.domain.repository.HistoryRepository
import com.analysis.domain.repository.PersonalityAnalyzeRepository
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
    fun bindPersonalityAnalyzeRepository(personalityAnalyzeRepositoryImpl: PersonalityAnalyzeRepositoryImpl): PersonalityAnalyzeRepository
}
