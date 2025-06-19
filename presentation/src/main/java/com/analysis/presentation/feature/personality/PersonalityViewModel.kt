package com.analysis.presentation.feature.personality

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.PersonalityAnalyzeUseCase
import com.analysis.presentation.R
import com.analysis.presentation.feature.personality.model.PersonalityUiState
import com.analysis.presentation.feature.personality.model.toPersonalityUiState
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
internal class PersonalityViewModel
    @Inject
    constructor(
        private val imageUtil: ImageUtil,
        private val personalityAnalyzeUseCase: PersonalityAnalyzeUseCase,
    ) : ViewModel() {
        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _errorMsgResId: MutableSharedFlow<Int> = MutableSharedFlow()
        val errorMsgResId: SharedFlow<Int> get() = _errorMsgResId.asSharedFlow()

        private val _personalityUiState =
            MutableStateFlow<PersonalityUiState>(PersonalityUiState.ImageUploadState)
        val personalityUiState: StateFlow<PersonalityUiState> = _personalityUiState.asStateFlow()

        private val _selectedImageUri = MutableStateFlow<Uri>(Uri.EMPTY)
        val selectedImageUri: StateFlow<Uri> = _selectedImageUri.asStateFlow()

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
                        _selectedImageUri.emit(uri)
                        return@collect
                    }
                    _errorMsgResId.emit(R.string.invalid_photo_no_text_or_no_korean)
                }
            }
        }

        fun removeVerificationUri() {
            _selectedImageUri.value = Uri.EMPTY
        }

        fun executeAnalysis() {
            _personalityUiState.value = PersonalityUiState.Analyzing.Loading

            val image = imageUtil.buildMultiPart(_selectedImageUri.value, IMAGE_PART_NAME)

            viewModelScope.launch {
                personalityAnalyzeUseCase(image).catch {
                    _personalityUiState.emit(PersonalityUiState.ImageUploadState)
                    _error.emit(it)
                }.collect {
                    _personalityUiState.emit(it.toPersonalityUiState())
                }
            }
        }

        companion object {
            private const val IMAGE_PART_NAME = "personality-file"
        }
    }
