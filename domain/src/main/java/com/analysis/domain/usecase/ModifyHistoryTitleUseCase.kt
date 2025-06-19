
package com.analysis.domain.usecase

import com.analysis.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModifyHistoryTitleUseCase
    @Inject
    constructor(
        private val historyRepository: HistoryRepository,
    ) {
        operator fun invoke(
            id: String,
            title: String,
        ): Flow<Unit> {
            return historyRepository.modifyHistoryTitle(id, title)
        }
    }
