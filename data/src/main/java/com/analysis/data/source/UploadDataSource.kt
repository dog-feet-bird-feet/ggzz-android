package com.analysis.data.source

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UploadDataSource {
    fun saveComparisons(
        images: List<MultipartBody.Part>,
    ): Flow<List<String>>

    fun saveVerification(
        image: MultipartBody.Part,
    ): Flow<String>
}
