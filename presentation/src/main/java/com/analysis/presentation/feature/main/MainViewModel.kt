package com.analysis.presentation.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.HasAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val hasAccessTokenUseCase: HasAccessTokenUseCase,
    ) : ViewModel() {
        private val _hasAccessToken: MutableStateFlow<Boolean?> = MutableStateFlow(null)
        val hasAccessToken: StateFlow<Boolean?> = _hasAccessToken.asStateFlow()

        fun getAccessTokenStatus() {
            viewModelScope.launch {
                hasAccessTokenUseCase().catch {
                    // 예외 처리 필요
                }.collect {
                    _hasAccessToken.emit(it)
                }
            }
        }
    }
