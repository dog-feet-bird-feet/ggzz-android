package com.analysis.data.di

import com.analysis.data.remote.source.HistoryDataSourceImpl
import com.analysis.data.source.HistoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface DataSourceModule {
    @Binds
    fun bindHistoryDataSource(historyDataSourceImpl: HistoryDataSourceImpl): HistoryDataSource
}
