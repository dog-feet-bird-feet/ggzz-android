package com.analysis.presentation.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.OpenableColumns
import com.analysis.presentation.R
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageUtil
    @Inject
    constructor(
        @ApplicationContext private val appContext: Context,
    ) {
        private val resolver: ContentResolver
            get() = appContext.contentResolver

        private val recognizer: TextRecognizer by lazy {
            TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        }

        fun isValidFormat(uri: Uri): Boolean {
            val size = resolver.openAssetFileDescriptor(uri, "r")?.use { it.length }
                ?: return false
            if (size > MAX_SIZE_BYTES) return false

            val mime = resolver.getType(uri)?.lowercase() ?: return false
            return mime in ALLOWED_MIME_TYPES
        }

        fun buildMultiPart(
            uri: Uri,
            partName: String = "",
        ): MultipartBody.Part {
            return uriToMultipart(partName, uri)
        }

        fun buildMultiParts(
            uris: List<Uri>,
            partName: String = "",
        ): List<MultipartBody.Part> {
            return uris.map { uri ->
                uriToMultipart(partName, uri)
            }
        }

        private fun uriToMultipart(
            partName: String,
            uri: Uri,
        ): MultipartBody.Part {
            // 실제 파일명 추출
            val fileName = uri.getFileName(resolver)

            // 바디 생성
            val bytes = resolver.openInputStream(uri)!!.readBytes()
            val mediaType = resolver.getType(uri)?.toMediaTypeOrNull() ?: "image/*".toMediaTypeOrNull()
            val body = bytes.toRequestBody(mediaType)

            // MultipartBody.Part 생성
            return MultipartBody.Part.createFormData(partName, fileName, body)
        }

        private fun Uri.getFileName(resolver: ContentResolver): String {
            return resolver.query(this, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
                ?.use { cursor ->
                    val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(idx)
                } ?: throw IllegalArgumentException("Invalid image URI: $this")
        }

        suspend fun analyzeImageHasTextWithKorean(
            uri: Uri,
            maxRetries: Int = 5,
            delayMs: Long = 1000L,
            isInitializeModelWork: Boolean = false,
        ): Flow<Boolean> {
            val image = if (isInitializeModelWork) createDummyImage() else InputImage.fromFilePath(appContext, uri)

            repeat(maxRetries) { attempt ->
                try {
                    val visionText = recognizer.process(image).await()
                    val recognizedText = visionText.text
                    if (recognizedText.isBlank()) {
                        return flow { emit(false) }
                    }
                    val hasAnyKorean = Regex(CHECK_KOREAN_REGEX)
                        .containsMatchIn(recognizedText)
                    return flow { emit(hasAnyKorean) }
                } catch (e: MlKitException) {
                    if (e.errorCode == MlKitException.UNAVAILABLE) {
                        if (attempt + 1 == maxRetries && !isInitializeModelWork) {
                            throw IllegalArgumentException(appContext.getString(R.string.error_try_later))
                        }
                        delay(delayMs)
                        return@repeat
                    } else {
                        throw IllegalArgumentException(appContext.getString(R.string.unknown_error_snackbar))
                    }
                }
            }

            throw IllegalArgumentException(appContext.getString(R.string.unknown_error_snackbar))
        }

        private fun createDummyImage() = InputImage.fromBitmap(
            Bitmap.createBitmap(32, 32, Bitmap.Config.ARGB_8888),
            0,
        )

        companion object {
            private const val MAX_SIZE_BYTES = 10L * 1024 * 1024
            private val ALLOWED_MIME_TYPES = listOf("image/png", "image/jpeg", "image/jpg")
            private const val CHECK_KOREAN_REGEX = ".*[\\uAC00-\\uD7AF].*"
        }
    }
