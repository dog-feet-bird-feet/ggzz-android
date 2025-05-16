package com.analysis.data.repository

import com.analysis.data.source.UploadDataSource
import com.analysis.domain.repository.UploadRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val uploadDataSource: UploadDataSource,
) : UploadRepository {
    override fun saveComparisons(images: List<MultipartBody.Part>): Flow<List<String>> {
        return uploadDataSource.saveComparisons(images)
    }

    override fun saveVerification(image: MultipartBody.Part): Flow<String> {
        return uploadDataSource.saveVerification(image)
    }
}
