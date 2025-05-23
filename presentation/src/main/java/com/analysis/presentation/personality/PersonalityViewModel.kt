package com.analysis.presentation.personality

import androidx.lifecycle.ViewModel
import com.analysis.presentation.feature.verify.model.UploadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PersonalityViewModel @Inject constructor() : ViewModel() {
    private val _uploadState = MutableStateFlow<UploadState>(UploadState.ComparisonUploadState)
    val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()
}
