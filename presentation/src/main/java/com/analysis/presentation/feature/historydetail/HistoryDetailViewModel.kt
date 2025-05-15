package com.analysis.presentation.feature.historydetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.usecase.FetchHistoryDetailUseCase
import com.analysis.presentation.feature.historydetail.model.HistoryDetailUiState
import com.analysis.presentation.feature.historydetail.model.toHistoryDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchHistoryDetailUseCase: FetchHistoryDetailUseCase,
) : ViewModel() {
    private val id = requireNotNull(savedStateHandle.get<String>("historyId"))

    private val _history = MutableStateFlow<HistoryDetailUiState>(HistoryDetailUiState.Loading)
    val history: StateFlow<HistoryDetailUiState> = _history.asStateFlow()


    init {
        fetchHistoryDetail()
    }

    private fun fetchHistoryDetail() {
        viewModelScope.launch {
            fetchHistoryDetailUseCase(id).catch {
                Log.e("seogi","HistoryViewModel: ${it.message}")
            }.collect {
                _history.emit(it.toHistoryDetailUiState())
            }
        }
    }
}
