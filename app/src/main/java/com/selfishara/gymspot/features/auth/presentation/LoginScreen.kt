package com.selfishara.gymspot.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfishara.gymspot.core.ui.components.base.AppButton
import com.selfishara.gymspot.core.ui.components.base.AppCard
import com.selfishara.gymspot.core.ui.components.base.AppTextField

/**
 * Login screen of the GymSpot application.
 *
 * Allows users to sign in using email and password.
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isPasswordVisible = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Welcome back",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Sign in to continue using GymSpot.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppTextField(
                    value = uiState.email,
                    onValueChange = viewModel::onEmailChanged,
                    label = "Email",
                    modifier = Modifier.fillMaxWidth()
                )

                AppTextField(
                    value = uiState.password,
                    onValueChange = viewModel::onPasswordChanged,
                    label = "Password",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true,
                    isPasswordVisible = isPasswordVisible.value,
                    onPasswordVisibilityToggle = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    },
                    imeAction = ImeAction.Done
                )

                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (uiState.isSuccess) {
                    Text(
                        text = "Login successful.",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                AppButton(
                    text = if (uiState.isLoading) "Signing in..." else "Login",
                    onClick = viewModel::onLoginClicked,
                    enabled = !uiState.isLoading &&
                            uiState.email.isNotBlank() &&
                            uiState.password.isNotBlank()
                )
            }
        }
    }
}