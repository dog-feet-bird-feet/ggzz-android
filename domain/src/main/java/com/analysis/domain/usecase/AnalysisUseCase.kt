package com.analysis.domain.usecase

import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.repository.AnalysisRepository
import com.analysis.domain.repository.UploadRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import okhttp3.MultipartBody
import javax.inject.Inject

class AnalysisUseCase
    @Inject
    constructor(
        private val uploadRepository: UploadRepository,
        private val analysisRepository: AnalysisRepository,
    ) {
        @OptIn(ExperimentalCoroutinesApi::class)
        operator fun invoke(
            comparisons: List<MultipartBody.Part>,
            verification: MultipartBody.Part,
        ): Flow<AnalysisResult> {
            return uploadRepository
                .saveComparisons(comparisons)
                .combine(uploadRepository.saveVerification(verification)) { list, single ->
                    list to single
                }
                .flatMapLatest { (comparisonUrls, verificationUrl) ->
                    analysisRepository.executeAnalysis(
                        verificationImageUrl = verificationUrl,
                        comparisonImageUrls = comparisonUrls,
                    )
                }
        }
    }
