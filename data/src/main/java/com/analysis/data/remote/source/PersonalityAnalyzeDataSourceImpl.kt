package com.analysis.data.remote.source

import com.analysis.data.remote.api.PersonalityApiService
import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityResponse
import com.analysis.data.source.PersonalityAnalyzeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonalityAnalyzeDataSourceImpl @Inject constructor(
    private val personalityApiService: PersonalityApiService,
) : PersonalityAnalyzeDataSource {
    override fun executeAnalyze(personalityAnalyzeRequest: PersonalityAnalyzeRequest): Flow<PersonalityResponse> {
        return flow {
            emit(
                personalityApiService.postPersonalityAnalyze(personalityAnalyzeRequest).body()
                    ?: throw IllegalArgumentException()
            )
        }
    }
}
