package com.selfishara.gymspot.features.exercises.presentation

import com.selfishara.gymspot.domain.model.Exercise

/**
 * Represents the UI state of the exercises screen.
 */
data class ExercisesUiState(
    val isLoading: Boolean = false,
    val exercises: List<Exercise> = emptyList(),
    val errorMessage: String? = null
)