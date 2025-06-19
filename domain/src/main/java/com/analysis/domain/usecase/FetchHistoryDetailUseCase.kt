package com.analysis.domain.usecase

import com.analysis.domain.model.HistoryDetail
import com.analysis.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchHistoryDetailUseCase
    @Inject
    constructor(
        private val historyRepository: HistoryRepository,
    ) {
        operator fun invoke(id: String): Flow<HistoryDetail> {
            return historyRepository.fetchHistoryDetail(id)
        }
    }
