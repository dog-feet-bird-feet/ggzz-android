package com.analysis.data.source

import com.analysis.data.remote.dto.response.ComparisonsResponse
import com.analysis.data.remote.dto.response.VerificationResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UploadDataSource {
    fun saveComparisons(
        images: List<MultipartBody.Part>,
    ): Flow<ComparisonsResponse>

    fun saveVerification(
        image: MultipartBody.Part,
    ): Flow<VerificationResponse>
}
