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
class HistoryViewModel
    @Inject
    constructor(
        private val fetchHistoriesUseCase: FetchHistoriesUseCase,
        private val modifyHistoryTitleUseCase: ModifyHistoryTitleUseCase,
        private val removeHistoryUseCase: RemoveHistoryUseCase,
    ) : ViewModel() {

        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

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
                    _error.emit(it)
                }.collect {
                    _histories.emit(it)
                }
            }
        }

        fun modifyHistoryTitle(
            id: String,
            title: String,
        ) {
            viewModelScope.launch {
                modifyHistoryTitleUseCase(id, title).catch {
                    _error.emit(it)
                    _isModifySuccess.emit(false)
                }.collect {
                    _isModifySuccess.emit(true)
                }
            }
        }

        fun removeHistory(id: String) {
            viewModelScope.launch {
                removeHistoryUseCase(id).catch {
                    _error.emit(it)
                    _isRemoveSuccess.emit(false)
                }.collect {
                    _isRemoveSuccess.emit(true)
                }
            }
        }
    }
