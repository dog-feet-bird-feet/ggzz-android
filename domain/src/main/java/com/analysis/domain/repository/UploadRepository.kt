package com.analysis.domain.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UploadRepository{
    fun saveComparisons(
        images: List<MultipartBody.Part>,
    ): Flow<List<String>>

    fun saveVerification(
        image: MultipartBody.Part,
    ): Flow<String>
}
