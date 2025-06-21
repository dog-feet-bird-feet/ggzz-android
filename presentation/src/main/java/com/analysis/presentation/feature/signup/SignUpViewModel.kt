package com.analysis.presentation.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.analysis.domain.usecase.CheckEmailUseCase
import com.analysis.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
    @Inject
    constructor(
        private val checkEmailUseCase: CheckEmailUseCase,
        private val signUpUseCase: SignUpUseCase,
    ) : ViewModel() {
        private val _error: MutableSharedFlow<Throwable> = MutableSharedFlow()
        val error: SharedFlow<Throwable> get() = _error.asSharedFlow()

        private val _isEmailAvailable = MutableStateFlow(false)
        val isEmailAvailable: StateFlow<Boolean> = _isEmailAvailable.asStateFlow()

        private val _isPasswordAvailable = MutableStateFlow(false)
        val isPasswordAvailable: StateFlow<Boolean> = _isPasswordAvailable.asStateFlow()

        private val _isConfirmedPasswordAvailable = MutableStateFlow(false)
        val isConfirmedPasswordAvailable: StateFlow<Boolean> =
            _isConfirmedPasswordAvailable.asStateFlow()

        val isFormAvailable: StateFlow<Boolean> = combine(
            _isEmailAvailable,
            _isPasswordAvailable,
            _isConfirmedPasswordAvailable,
        ) { emailValid, passwordValid, confirmValid ->
            emailValid && passwordValid && confirmValid
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false,
        )

        private val _isSignUpSuccess = MutableStateFlow(false)
        val isSignUpSuccess: StateFlow<Boolean> = _isSignUpSuccess.asStateFlow()

        fun checkEmail(email: String) {
            viewModelScope.launch {
                checkEmailUseCase(email).catch {
                    _error.emit(it)
                }.collect {
                    _isEmailAvailable.value = it
                }
            }
        }

        fun changeEmailNotAvailable(){
            _isEmailAvailable.value = false
        }

        fun isValidPassword(password: String) {
            _isPasswordAvailable.value = PASSWORD_REGEX.matches(password)
        }

        fun isValidConfirmedPassword(password: String, confirmedPassword: String) {
            _isConfirmedPasswordAvailable.value =
                confirmedPassword.isNotBlank() && password == confirmedPassword
        }

        fun signUp(email: String, password: String) {
            viewModelScope.launch {
                signUpUseCase(email, password).catch {
                    _error.emit(it)
                }.collect {
                    _isSignUpSuccess.value = it
                }
            }
        }

        companion object {
            // 특수문자 1개 이상 포함
            private const val SPECIAL_CHAR_CONDITION = "(?=.*[!@#\$%^&*(),.?\":{}|<>])"

            // 허용 문자 + 길이 제한 (10~20자)
            private const val ALLOWED_CHAR_AND_LENGTH = "[A-Za-z\\d!@#\$%^&*(),.?\":{}|<>]{10,20}"

            // 전체 정규식
            private val PASSWORD_REGEX = Regex("^$SPECIAL_CHAR_CONDITION$ALLOWED_CHAR_AND_LENGTH$")
        }
    }
