package com.selfishara.gymspot.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.core.ui.components.base.AppButton
import com.selfishara.gymspot.data.repository.AuthRepositoryImpl
import com.selfishara.gymspot.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch

/**
 * Profile screen of the GymSpot application.
 *
 * Temporarily includes a logout action for authentication flow testing.
 */
@Composable
fun ProfileScreen() {
    val coroutineScope = rememberCoroutineScope()
    val signOutUseCase = remember { SignOutUseCase(AuthRepositoryImpl()) }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        AppButton(
            text = if (isLoading) "Logging out..." else "Logout",
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    errorMessage = null

                    when (val result = signOutUseCase()) {
                        is ResultWrapper.Success -> {
                            isLoading = false
                        }
                        is ResultWrapper.Error -> {
                            isLoading = false
                            errorMessage = result.message
                        }
                        is ResultWrapper.Loading -> {
                            isLoading = true
                        }
                    }
                }
            },
            enabled = !isLoading
        )
    }
}