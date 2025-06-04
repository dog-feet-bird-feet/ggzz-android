package com.analysis.presentation.feature.verify

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
internal class VerifyViewModel
    @Inject
    constructor(
        private val analysisUseCase: AnalysisUseCase,
    ) : ViewModel() {
        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
        val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

        private val _selectedVerificationUri = MutableStateFlow<Uri?>(null)
        val selectedVerificationUri: StateFlow<Uri?> = _selectedVerificationUri.asStateFlow()

        private val _uiState = MutableStateFlow<VerificationUiState>(VerificationUiState.ComparisonUploadState)
        val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()

        fun moveToVerificationUpload() {
            _uiState.value = VerificationUiState.VerificationUploadState
        }

        fun moveToComparisonUpload() {
            _uiState.value = VerificationUiState.ComparisonUploadState
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

        fun executeAnalysis(
            comparisons: List<MultipartBody.Part>,
            verification: MultipartBody.Part,
        ) {
            _uiState.value = VerificationUiState.Verification.Loading

            viewModelScope.launch {
                analysisUseCase(comparisons, verification).catch {
                    _error.emit(it)
                }.collect {
                    _uiState.emit(it.toVerificationResultUiState())
                }
            }
        }
    }
