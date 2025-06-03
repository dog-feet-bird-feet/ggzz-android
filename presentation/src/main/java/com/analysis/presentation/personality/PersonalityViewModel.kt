package com.analysis.presentation.personality

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.PersonalityAnalyzeUseCase
import com.analysis.presentation.personality.model.PersonalityUiState
import com.analysis.presentation.personality.model.toPersonalityUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class PersonalityViewModel
    @Inject
    constructor(
        private val personalityAnalyzeUseCase: PersonalityAnalyzeUseCase,
    ) : ViewModel() {
        private val _personalityUiState =
            MutableStateFlow<PersonalityUiState>(PersonalityUiState.ImageUploadState)
        val personalityUiState: StateFlow<PersonalityUiState> = _personalityUiState.asStateFlow()

        private val _selectedImageUri = MutableStateFlow<Uri?>(null)
        val selectedImageUri: StateFlow<Uri?> = _selectedImageUri.asStateFlow()

        fun updatePickedVerificationUri(uri: Uri) {
            _selectedImageUri.value = uri
        }

        fun removeVerificationUri() {
            _selectedImageUri.value = null
        }

        fun executeAnalysis(image: MultipartBody.Part) {
            _personalityUiState.value = PersonalityUiState.Loading

            viewModelScope.launch {
                personalityAnalyzeUseCase(image).catch {
                    // 에러 핸들링 필요
                    Log.e("seogi", it.message.toString())
                }.collect {
                    _personalityUiState.emit(it.toPersonalityUiState())
                }
            }
        }
    }
