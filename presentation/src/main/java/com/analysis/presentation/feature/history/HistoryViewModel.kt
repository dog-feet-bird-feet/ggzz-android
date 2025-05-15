package com.analysis.presentation.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.model.History
import com.analysis.domain.usecase.FetchHistoriesUseCase
import com.analysis.domain.usecase.ModifyHistoryTitleUseCase
import com.analysis.domain.usecase.RemoveHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val fetchHistoriesUseCase: FetchHistoriesUseCase,
    private val modifyHistoryTitleUseCase: ModifyHistoryTitleUseCase,
    private val removeHistoryUseCase: RemoveHistoryUseCase,
) : ViewModel() {
    private val _histories = MutableStateFlow<List<History>>(emptyList())
    val histories: StateFlow<List<History>> = _histories.asStateFlow()

    init {
//        fetchHistories()
    }

    private fun fetchHistories() {
        viewModelScope.launch {
            fetchHistoriesUseCase().catch {
                // 에러 핸들링 필요
            }.collect {
                _histories.emit(it)
            }
        }
    }
}
