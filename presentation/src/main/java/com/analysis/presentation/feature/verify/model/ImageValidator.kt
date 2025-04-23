package com.analysis.presentation.feature.verify.model

import android.content.ContentResolver
import android.net.Uri

object ImageValidator {
    private const val MAX_SIZE_BYTES = 2 * 1024 * 1024
    private val ALLOWED_MIME_TYPES = listOf("image/png", "image/jpeg", "image/jpg")

    fun isValid(
        uri: Uri,
        contentResolver: ContentResolver,
    ): Boolean {
        val size = contentResolver.openAssetFileDescriptor(uri, "r")?.use { it.length } ?: return false
        if (size > MAX_SIZE_BYTES) return false

        val type = contentResolver.getType(uri)?.lowercase() ?: return false
        return type in ALLOWED_MIME_TYPES
    }
}
