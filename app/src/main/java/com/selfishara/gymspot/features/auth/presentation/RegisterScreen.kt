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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfishara.gymspot.core.ui.components.base.AppButton
import com.selfishara.gymspot.core.ui.components.base.AppCard
import com.selfishara.gymspot.core.ui.components.base.AppTextField 

/**
 * Register screen of the GymSpot application.
 *
 * Allows users to create a new account using email and password.
 */
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    val isPasswordVisible= rememberSaveable { mutableStateOf(false) }
    val isConfirmPasswordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ){
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Sign up to start tracking your workouts.",
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
                    }
                )

                AppTextField(
                    value = uiState.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChanged,
                    label = "Confirm password",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true,
                    isPasswordVisible = isConfirmPasswordVisible.value,
                    onPasswordVisibilityToggle = {
                    isConfirmPasswordVisible.value = !isConfirmPasswordVisible.value
                    }
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
                        text = "Account created successfully.",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                AppButton(
                    text = if (uiState.isLoading) "Creating account..." else "Create account",
                    onClick = viewModel::onRegisterClicked,
                    enabled = !uiState.isLoading &&
                            uiState.email.isNotBlank() &&
                            uiState.password.isNotBlank() &&
                            uiState.confirmPassword.isNotBlank()
                )
            }
        }
    }
}