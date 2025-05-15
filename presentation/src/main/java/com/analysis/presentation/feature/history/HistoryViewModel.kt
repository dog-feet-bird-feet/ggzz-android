package com.analysis.presentation.feature.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.model.History
import com.analysis.domain.usecase.FetchHistoriesUseCase
import com.analysis.domain.usecase.ModifyHistoryTitleUseCase
import com.analysis.domain.usecase.RemoveHistoryUseCase
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
class HistoryViewModel @Inject constructor(
    private val fetchHistoriesUseCase: FetchHistoriesUseCase,
    private val modifyHistoryTitleUseCase: ModifyHistoryTitleUseCase,
    private val removeHistoryUseCase: RemoveHistoryUseCase,
) : ViewModel() {
    private val _histories = MutableStateFlow<List<History>>(emptyList())
    val histories: StateFlow<List<History>> = _histories.asStateFlow()

    private val _isModifySuccess = MutableSharedFlow<Boolean>()
    val isModifySuccess: SharedFlow<Boolean> = _isModifySuccess.asSharedFlow()

    private val _isRemoveSuccess = MutableSharedFlow<Boolean>()
    val isRemoveSuccess: SharedFlow<Boolean> = _isRemoveSuccess.asSharedFlow()


    init {
        fetchHistories()
    }

    fun fetchHistories() {
        viewModelScope.launch {
            fetchHistoriesUseCase().catch {
                // 에러 핸들링 필요
                Log.e("seogi","HistoryViewModel: ${it.message}")
            }.collect {
                _histories.emit(it)
            }
        }
    }

    fun modifyHistoryTitle(id: String, title: String) {
        viewModelScope.launch {
            modifyHistoryTitleUseCase(id, title).catch {
                _isModifySuccess.emit(false)
            }.collect {
                _isModifySuccess.emit(true)
            }
        }
    }

    fun removeHistory(id: String) {
        viewModelScope.launch {
            removeHistoryUseCase(id).catch {
                _isRemoveSuccess.emit(false)
            }.collect {
                _isRemoveSuccess.emit(true)
            }
        }
    }
}
