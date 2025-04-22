package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.analysis.presentation.feature.verify.model.UploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor() : ViewModel() {
    private val _uploadState = MutableStateFlow<UploadState>(UploadState.ComparisonUploadState)
    val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

    private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
    val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

    private val _selectedVerificationUri = MutableStateFlow<Uri?>(null)
    val selectedVerificationUri: StateFlow<Uri?> = _selectedVerificationUri.asStateFlow()

    fun changeUploadState() {
        when (_uploadState.value) {
            UploadState.ComparisonUploadState -> _uploadState.value =
                UploadState.VerificationUploadState

            UploadState.VerificationUploadState -> _uploadState.value =
                UploadState.ComparisonUploadState
        }
    }

    fun updatePickedComparisonUris(uris: List<Uri>) {
        _selectedComparisonUris.value = uris
    }

    fun removeComparisonUri(uri: Uri) {
        _selectedComparisonUris.value -= uri
    }

    fun updatePickedVerificationUri(uri: Uri) {
        _selectedVerificationUri.value = uri
    }

    fun removeVerificationUri(uri: Uri) {
        _selectedVerificationUri.value = null
    }
}
