package com.analysis.data.remote.source

import com.analysis.data.remote.api.PersonalityApiService
import com.analysis.data.remote.dto.request.PersonalityAnalyzeRequest
import com.analysis.data.remote.dto.response.PersonalityImageResponse
import com.analysis.data.remote.dto.response.PersonalityResponse
import com.analysis.data.source.PersonalityDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonalityDataSourceImpl
    @Inject
    constructor(
        private val personalityApiService: PersonalityApiService,
    ) : PersonalityDataSource {
        override fun uploadImage(image: MultipartBody.Part): Flow<PersonalityImageResponse> {
            return flow {
                emit(
                    personalityApiService.postUploadImage(image).body()
                        ?: throw IllegalArgumentException(),
                )
            }
        }

        override fun executeAnalyze(personalityAnalyzeRequest: PersonalityAnalyzeRequest): Flow<PersonalityResponse> {
            return flow {
                emit(
                    personalityApiService.postPersonalityAnalyze(personalityAnalyzeRequest).body()
                        ?: throw IllegalArgumentException(),
                )
            }
        }
    }
