package com.analysis.presentation.feature.historydetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.model.AnalysisResult
import com.analysis.domain.usecase.FetchHistoryDetailUseCase
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

    private val _history = MutableStateFlow<AnalysisResult?>(null)
    val history: StateFlow<AnalysisResult?> = _history.asStateFlow()


    init {
        Log.e("seogi", id)
//        fetchHistoryDetail()
    }

    private fun fetchHistoryDetail() {
        viewModelScope.launch {
            fetchHistoryDetailUseCase(id).catch {
                // 에러 핸들링 필요
            }.collect {
                _history.emit(it)
            }
        }
    }

}
