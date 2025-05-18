package com.analysis.presentation.feature.verify.model

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

object ImageMultipartUtil {
    private const val MAX_SIZE_BYTES = 10L * 1024 * 1024
    private val ALLOWED_MIME_TYPES = setOf("image/png", "image/jpeg", "image/jpg")

    fun isValid(
        uri: Uri,
        resolver: ContentResolver,
    ): Boolean {
        val size = resolver.openAssetFileDescriptor(uri, "r")?.use { it.length }
            ?: return false
        if (size > MAX_SIZE_BYTES) return false

        val mime = resolver.getType(uri)?.lowercase() ?: return false
        return mime in ALLOWED_MIME_TYPES
    }

    fun uriToMultipart(
        partName: String,
        uri: Uri,
        resolver: ContentResolver,
    ): MultipartBody.Part {
        // 1) 실제 파일명 추출
        val fileName = resolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.use { cursor ->
                val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                cursor.getString(idx)
            } ?: "file.jpg"

        // 2) 바디 생성
        val bytes = resolver.openInputStream(uri)!!.readBytes()
        val mediaType = resolver.getType(uri)?.toMediaTypeOrNull() ?: "image/*".toMediaTypeOrNull()
        val body = bytes.toRequestBody(mediaType)

        // 3) MultipartBody.Part 생성
        return MultipartBody.Part.createFormData(partName, fileName, body)
    }
}
