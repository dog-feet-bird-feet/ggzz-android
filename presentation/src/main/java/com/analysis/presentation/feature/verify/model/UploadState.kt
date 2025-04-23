package com.analysis.presentation.feature.verify.model

sealed interface UploadState {
    data object ComparisonUploadState : UploadState

    data object VerificationUploadState : UploadState
}
