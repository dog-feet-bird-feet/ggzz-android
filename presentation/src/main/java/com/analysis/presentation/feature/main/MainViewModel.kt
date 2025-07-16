package com.analysis.presentation.feature.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.HasAccessTokenUseCase
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
class MainViewModel
    @Inject
    constructor(
        private val hasAccessTokenUseCase: HasAccessTokenUseCase,
        private val imageUtil: ImageUtil,
    ) : ViewModel() {
        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _hasAccessToken: MutableStateFlow<Boolean?> = MutableStateFlow(null)
        val hasAccessToken: StateFlow<Boolean?> = _hasAccessToken.asStateFlow()

        fun getAccessTokenStatus() {
            viewModelScope.launch {
                hasAccessTokenUseCase().catch { throwable ->
                    _error.emit(throwable)
                }.collect {
                    _hasAccessToken.emit(it)
                }
            }
        }

        fun initializeTextRecognitionModel() {
            viewModelScope.launch {
                imageUtil.analyzeImageHasTextWithKorean(Uri.EMPTY, isInitializeModelWork = true).catch { throwable ->
                    _error.emit(throwable)
                }.collect {}
            }
        }
    }
