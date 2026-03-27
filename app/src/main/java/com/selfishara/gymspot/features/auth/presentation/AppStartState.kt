package com.selfishara.gymspot.features.auth.presentation

/**
 * Represents the authentication state of the app during startup.
 */
sealed interface AppStartState {
    data object Loading : AppStartState
    data object Authenticated : AppStartState
    data object Unauthenticated : AppStartState
}