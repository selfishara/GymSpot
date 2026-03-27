package com.selfishara.gymspot.features.auth.presentation

/**
 * Represents the UI state for the login screen.
 *
 * @property email The user's email input.
 * @property password The user's password input.
 * @property isLoading Indicates whether a login request is in progress.
 * @property isSuccess Indicates whether the login was successful.
 * @property errorMessage An optional error message to display if login fails.
 */
data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)