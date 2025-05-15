package com.analysis.domain.usecase

import com.analysis.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveHistoryUseCase
    @Inject
    constructor(
        private val historyRepository: HistoryRepository,
    ) {
        operator fun invoke(id: String): Flow<Unit> {
            return historyRepository.removeHistory(id)
        }
    }
