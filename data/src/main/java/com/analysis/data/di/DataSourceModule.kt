package com.analysis.data.di

import com.analysis.data.remote.source.AnalysisDataSourceImpl
import com.analysis.data.remote.source.HistoryDataSourceImpl
import com.analysis.data.remote.source.PersonalityAnalyzeDataSourceImpl
import com.analysis.data.remote.source.UploadDataSourceImpl
import com.analysis.data.source.AnalysisDataSource
import com.analysis.data.source.HistoryDataSource
import com.analysis.data.source.PersonalityAnalyzeDataSource
import com.analysis.data.source.UploadDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface DataSourceModule {
    @Binds
    fun bindHistoryDataSource(historyDataSourceImpl: HistoryDataSourceImpl): HistoryDataSource

    @Binds
    fun bindUploadDataSource(uploadDataSourceImpl: UploadDataSourceImpl): UploadDataSource

    @Binds
    fun binAnalysisDataSource(analysisDataSourceImpl: AnalysisDataSourceImpl): AnalysisDataSource

    @Binds
    fun binPersonalityAnalyzeDataSource(personalityAnalyzeDataSourceImpl: PersonalityAnalyzeDataSourceImpl): PersonalityAnalyzeDataSource
}
