package com.analysis.data.di

import com.analysis.data.remote.source.AnalysisDataSourceImpl
import com.analysis.data.remote.source.HistoryDataSourceImpl
import com.analysis.data.remote.source.LoginDataSourceImpl
import com.analysis.data.remote.source.PersonalityDataSourceImpl
import com.analysis.data.remote.source.UploadDataSourceImpl
import com.analysis.data.source.AnalysisDataSource
import com.analysis.data.source.HistoryDataSource
import com.analysis.data.source.LoginDataSource
import com.analysis.data.source.PersonalityDataSource
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
    fun bindAnalysisDataSource(analysisDataSourceImpl: AnalysisDataSourceImpl): AnalysisDataSource

    @Binds
    fun bindPersonalityAnalyzeDataSource(personalityAnalyzeDataSourceImpl: PersonalityDataSourceImpl): PersonalityDataSource

    @Binds
    fun bindLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl): LoginDataSource
}
