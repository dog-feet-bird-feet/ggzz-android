package com.analysis.presentation.feature.historydetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.FetchHistoryDetailUseCase
import com.analysis.presentation.feature.historydetail.model.HistoryDetailUiState
import com.analysis.presentation.feature.historydetail.model.toHistoryDetailUiState
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
class HistoryDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val fetchHistoryDetailUseCase: FetchHistoryDetailUseCase,
    ) : ViewModel() {
        private val id = requireNotNull(savedStateHandle.get<String>("historyId"))

        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _history = MutableStateFlow<HistoryDetailUiState>(HistoryDetailUiState.Loading)
        val history: StateFlow<HistoryDetailUiState> = _history.asStateFlow()

        init {
            fetchHistoryDetail()
        }

        private fun fetchHistoryDetail() {
            viewModelScope.launch {
                fetchHistoryDetailUseCase(id).catch {
                    _error.emit(it)
                }.collect {
                    _history.emit(it.toHistoryDetailUiState())
                }
            }
        }
    }
