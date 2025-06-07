package com.analysis.presentation.feature.verify

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.R
import com.analysis.presentation.feature.verify.model.VerificationUiState
import com.analysis.presentation.feature.verify.model.toVerificationResultUiState
import com.analysis.presentation.util.ImageUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class VerifyViewModel
    @Inject
    constructor(
        private val imageUtil: ImageUtil,
        private val analysisUseCase: AnalysisUseCase,
    ) : ViewModel() {
        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _errorMsgResId: MutableSharedFlow<Int> = MutableSharedFlow()
        val errorMsgResId: SharedFlow<Int> get() = _errorMsgResId.asSharedFlow()

        private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
        val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

        private val _selectedVerificationUri = MutableStateFlow<Uri>(Uri.EMPTY)
        val selectedVerificationUri: StateFlow<Uri> = _selectedVerificationUri.asStateFlow()

        private val _uiState =
            MutableStateFlow<VerificationUiState>(VerificationUiState.ComparisonUploadState)
        val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()

        fun moveToVerificationUpload() {
            _uiState.value = VerificationUiState.VerificationUploadState
        }

        fun moveToComparisonUpload() {
            _uiState.value = VerificationUiState.ComparisonUploadState
        }

        fun updatePickedComparisonUris(uris: List<Uri>) {
            viewModelScope.launch {
                val validUris = uris.filter { imageUtil.isValidFormat(it) }.toMutableList()

                if (uris.size != validUris.size) {
                    _errorMsgResId.emit(R.string.invalid_photo_format)
                }

                val finalUris = mutableListOf<Uri>()
                var isErrorEmitted = false
                var sawTextError = false
                validUris.forEach { uri ->
                    imageUtil.analyzeImageHasTextWithKorean(uri).catch {
                        if (!isErrorEmitted) {
                            _error.emit(it)
                            isErrorEmitted = true
                        }
                    }.collect { hasTextWithKorean ->
                        if (hasTextWithKorean) {
                            finalUris.add(uri)
                            return@collect
                        }
                        sawTextError = true
                    }
                }
                if (sawTextError) _errorMsgResId.emit(R.string.invalid_photo_no_text_or_no_korean)
                _selectedComparisonUris.emit(finalUris)
            }
        }

        fun removeComparisonUri(uri: Uri) {
            _selectedComparisonUris.value -= uri
        }

        fun updatePickedVerificationUri(uri: Uri) {
            viewModelScope.launch {
                if (!imageUtil.isValidFormat(uri)) {
                    _errorMsgResId.emit(R.string.invalid_photo_format)
                    return@launch
                }

                imageUtil.analyzeImageHasTextWithKorean(uri).catch {
                    _error.emit(it)
                }.collect { hasTextWithKorean ->
                    if (hasTextWithKorean) {
                        _selectedVerificationUri.emit(uri)
                        return@collect
                    }
                    _errorMsgResId.emit(R.string.invalid_photo_no_text_or_no_korean)
                }
            }
        }

        fun removeVerificationUri() {
            _selectedVerificationUri.value = Uri.EMPTY
        }

        fun executeAnalysis() {
            _uiState.value = VerificationUiState.Verification.Loading

            val comparisons = imageUtil.buildMultiParts(
                selectedComparisonUris.value,
                COMPARISON_IMAGE_PART_NAME,
            )

            val verification = imageUtil.buildMultiPart(
                selectedVerificationUri.value,
                VERIFICATION_IMAGE_PART_NAME,
            )

            viewModelScope.launch {
                analysisUseCase(comparisons, verification).catch { throwable ->
                    _uiState.value = VerificationUiState.VerificationUploadState
                    _error.emit(throwable)
                }.collect { analysisResult ->
                    _uiState.emit(analysisResult.toVerificationResultUiState())
                }
            }
        }

        companion object {
            private const val COMPARISON_IMAGE_PART_NAME = "comparison-file"
            private const val VERIFICATION_IMAGE_PART_NAME = "verification-file"
        }
    }
