package com.analysis.data.di

import com.analysis.data.repository.HistoryRepositoryImpl
import com.analysis.data.source.HistoryDataSource
import com.analysis.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindGoalRepository(historyRepositoryImpl: HistoryRepositoryImpl): HistoryRepository
}
