package com.selfishara.gymspot.features.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.selfishara.gymspot.core.network.SupabaseClientProvider
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus

/**
 * Observes the Supabase session status and converts it into
 * a simplified app start state for navigation decisions.
 */
@Composable
fun rememberAppStartState(): androidx.compose.runtime.State<AppStartState> {
    val state = remember {
        mutableStateOf<AppStartState>(AppStartState.Loading)
    }

    LaunchedEffect(Unit) {
        SupabaseClientProvider.client.auth.sessionStatus.collect { status ->
            state.value = when (status) {
                SessionStatus.Initializing -> AppStartState.Loading
                is SessionStatus.Authenticated -> AppStartState.Authenticated
                is SessionStatus.NotAuthenticated -> AppStartState.Unauthenticated
                is SessionStatus.RefreshFailure -> AppStartState.Unauthenticated
            }
        }
    }

    return state
}