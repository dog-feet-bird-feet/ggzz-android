package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.feature.verify.model.UploadState
import com.analysis.presentation.feature.verify.model.VerificationResultUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel
    @Inject
    constructor(
        private val analysisUseCase: AnalysisUseCase,
    ) : ViewModel() {
        private val _uploadState = MutableStateFlow<UploadState>(UploadState.ComparisonUploadState)
        val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

        private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
        val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

        private val _selectedVerificationUri = MutableStateFlow<Uri?>(null)
        val selectedVerificationUri: StateFlow<Uri?> = _selectedVerificationUri.asStateFlow()

        private val _verificationResultUiState =
            MutableStateFlow<VerificationResultUiState>(VerificationResultUiState.Loading)
        val verificationResultUiState: StateFlow<VerificationResultUiState> =
            _verificationResultUiState.asStateFlow()

        fun changeUploadState() {
            when (_uploadState.value) {
                UploadState.ComparisonUploadState ->
                    _uploadState.value =
                        UploadState.VerificationUploadState

                UploadState.VerificationUploadState ->
                    _uploadState.value =
                        UploadState.ComparisonUploadState

                UploadState.ResultState ->
                    _uploadState.value = UploadState.ResultState
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

        fun executeAnalysis(
            comparisons: List<MultipartBody.Part>,
            verification: MultipartBody.Part,
        ) {
            _uploadState.value = UploadState.ResultState

            viewModelScope.launch {
                analysisUseCase(comparisons, verification).catch {
                }.collect {
                    _verificationResultUiState.emit(it.toVerificationResultUiState())
                }
            }
        }
    }
