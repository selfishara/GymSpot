package com.selfishara.gymspot.features.auth.presentation

/**
 * Represents the UI state of the register screen.
 */
data class RegisterUiState (
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
