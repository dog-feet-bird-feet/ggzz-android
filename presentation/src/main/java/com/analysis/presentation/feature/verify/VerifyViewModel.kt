package com.analysis.presentation.feature.verify

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.usecase.AnalysisUseCase
import com.analysis.presentation.feature.verify.model.UploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val analysisUseCase: AnalysisUseCase,
) : ViewModel() {
    private val _uploadState = MutableStateFlow<UploadState>(UploadState.ComparisonUploadState)
    val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

    private val _selectedComparisonUris = MutableStateFlow<List<Uri>>(emptyList())
    val selectedComparisonUris: StateFlow<List<Uri>> = _selectedComparisonUris.asStateFlow()

    private val _selectedVerificationUri = MutableStateFlow<Uri?>(null)
    val selectedVerificationUri: StateFlow<Uri?> = _selectedVerificationUri.asStateFlow()

    private val _analysisResult = MutableStateFlow<AnalysisResult?>(null)
    val analysisResult: StateFlow<AnalysisResult?> = _analysisResult.asStateFlow()

    fun changeUploadState() {
        when (_uploadState.value) {
            UploadState.ComparisonUploadState ->
                _uploadState.value =
                    UploadState.VerificationUploadState

            UploadState.VerificationUploadState ->
                _uploadState.value =
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

    fun executeAnalysis(
        comparisons: List<MultipartBody.Part>,
        verification: MultipartBody.Part,
    ) {
        viewModelScope.launch {
            analysisUseCase(comparisons, verification).catch {
                Log.e("seogi","executeAnalysis error: ${it}")
                Log.e("seogi","executeAnalysis errorMSG: ${it.message}")
            }.collect {
                Log.e("seogi","executeAnalysis result: ${it}")
                _analysisResult.emit(it)
            }
        }
    }
}
