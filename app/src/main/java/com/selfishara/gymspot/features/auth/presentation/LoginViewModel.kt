package com.selfishara.gymspot.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.data.repository.AuthRepositoryImpl
import com.selfishara.gymspot.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the login screen.
 *
 * Handles the input state of the login form
 * and coordinates the sign in process.
 */
class LoginViewModel : ViewModel() {

    private val signInUseCase = SignInUseCase(AuthRepositoryImpl())

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            errorMessage = null,
            isSuccess = false
        )
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null,
            isSuccess = false
        )
    }

    fun onLoginClicked() {
        val currentState = _uiState.value

        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _uiState.value = currentState.copy(
                errorMessage = "Email and password must not be empty."
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(
                isLoading = true,
                errorMessage = null,
                isSuccess = false
            )

            when (val result = signInUseCase(currentState.email, currentState.password)) {
                is ResultWrapper.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null
                    )
                }

                is ResultWrapper.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = result.message
                    )
                }

                is ResultWrapper.Loading -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }
}