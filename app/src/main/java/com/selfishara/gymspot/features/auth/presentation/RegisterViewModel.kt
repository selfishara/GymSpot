package com.selfishara.gymspot.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.data.repository.AuthRepositoryImpl
import com.selfishara.gymspot.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the register screen.
 *
 * Handles the input state of the registration form
 * and coordinates the sign up process.
 */
class RegisterViewModel : ViewModel() {

    private val signUpUseCase = SignUpUseCase(AuthRepositoryImpl())

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            errorMessage = null,
            isSuccess = false
        )
    }

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

    fun onRegisterClicked() {
        val currentState = _uiState.value

        // 1. Campos vacíos
        if (currentState.email.isBlank() ||
            currentState.password.isBlank() ||
            currentState.confirmPassword.isBlank()
        ) {
            _uiState.value = currentState.copy(
                errorMessage = "All fields are required."
            )
            return
        }

        // 2. Passwords iguales
        if (currentState.password != currentState.confirmPassword) {
            _uiState.value = currentState.copy(
                errorMessage = "Passwords do not match."
            )
            return
        }

        // 3. Validación básica de password
        if (currentState.password.length < 6) {
            _uiState.value = currentState.copy(
                errorMessage = "Password must be at least 6 characters."
            )
            return
        }

        // 4. Llamada real
        viewModelScope.launch {
            _uiState.value = currentState.copy(
                isLoading = true,
                errorMessage = null,
                isSuccess = false
            )

            when (val result = signUpUseCase(currentState.email, currentState.password)) {

                is ResultWrapper.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }

                is ResultWrapper.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
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