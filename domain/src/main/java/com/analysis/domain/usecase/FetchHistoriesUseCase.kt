package com.analysis.domain.usecase

import com.analysis.domain.model.History
import com.analysis.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchHistoriesUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(): Flow<List<History>> {
        return historyRepository.fetchHistories()
    }
}
