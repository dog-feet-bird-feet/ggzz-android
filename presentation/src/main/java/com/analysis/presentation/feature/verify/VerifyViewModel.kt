package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor() : ViewModel() {
    private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
    val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

    private val _selectedVerificationUris = MutableStateFlow<Uri?>(null)
    val selectedVerificationUris: StateFlow<Uri?> = _selectedVerificationUris.asStateFlow()

    fun updatePickedComparisonUris(uris: List<Uri>) {
        _selectedComparisonUris.value = uris
    }

    fun removeComparisonUri(uri: Uri) {
        _selectedComparisonUris.value -= uri
    }

    fun updatePickedVerificationUri(uri: Uri) {
        _selectedVerificationUris.value = uri
    }

    fun removeVerificationUri(uri: Uri) {
        _selectedVerificationUris.value = null
    }
}
