package com.analysis.domain.usecase

import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.model.History
import com.analysis.domain.repository.AnalysisRepository
import com.analysis.domain.repository.HistoryRepository
import com.analysis.domain.repository.UploadRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import okhttp3.MultipartBody
import javax.inject.Inject

class FetchHistoriesUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(): Flow<List<History>> {
        return historyRepository.fetchHistories()
    }
}
