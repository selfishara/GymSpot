package com.selfishara.gymspot.features.exercises.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfishara.gymspot.core.ui.components.base.AppCard

/**
 * Exercises screen of the GymSpot application.
 *
 * Displays the list of exercises fetched from the remote API.
 */
@Composable
fun ExerciseScreen(
        viewModel: ExercisesViewModel = viewModel()
) {
        val uiState by viewModel.uiState.collectAsState()

        when {
                uiState.isLoading -> {
                        Column(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                verticalArrangement = Arrangement.Center
                        ) {
                                Text(
                                        text = "Loading exercises...",
                                        style = MaterialTheme.typography.bodyLarge
                                )
                        }
                }

                uiState.errorMessage != null -> {
                        Column(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                verticalArrangement = Arrangement.Center
                        ) {
                                Text(
                                        text = uiState.errorMessage ?: "Unknown error",
                                        color = MaterialTheme.colorScheme.error,
                                        style = MaterialTheme.typography.bodyLarge
                                )
                        }
                }

                else -> {
                        LazyColumn(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                                items(uiState.exercises) { exercise ->
                                        AppCard(
                                                modifier = Modifier.fillMaxWidth()
                                        ) {
                                                Column(
                                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                                ) {
                                                        Text(
                                                                text = exercise.name,
                                                                style = MaterialTheme.typography.titleLarge
                                                        )

                                                        Text(
                                                                text = exercise.category,
                                                                style = MaterialTheme.typography.bodyMedium,
                                                                color = MaterialTheme.colorScheme.primary
                                                        )

                                                        if (exercise.description.isNotBlank()) {
                                                                Text(
                                                                        text = exercise.description,
                                                                        style = MaterialTheme.typography.bodyMedium,
                                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                                )
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
}