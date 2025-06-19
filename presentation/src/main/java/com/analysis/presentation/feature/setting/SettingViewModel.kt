package com.analysis.presentation.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    private val _isLogoutSuccess = MutableStateFlow(false)
    val isLogoutSuccess: StateFlow<Boolean> = _isLogoutSuccess.asStateFlow()

    fun logout(){
        viewModelScope.launch {
            logoutUseCase().catch {
                // 예외 처리 필요
            }.collect{
                _isLogoutSuccess.value = it
            }
        }
    }
}
