package com.analysis.data.remote.source

import com.analysis.data.remote.api.UploadApiService
import com.analysis.data.source.UploadDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadDataSourceImpl @Inject constructor(
    private val uploadApiService: UploadApiService,
) : UploadDataSource {
    override fun saveComparisons(images: List<MultipartBody.Part>): Flow<List<String>> {
        return flow {
            emit(
                uploadApiService.postComparisons(images).body() ?: throw IllegalArgumentException()
            )
        }
    }

    override fun saveVerification(image: MultipartBody.Part): Flow<String> {
        return flow {
            emit(
                uploadApiService.postVerification(image).body() ?: throw IllegalArgumentException()
            )
        }
    }
}
