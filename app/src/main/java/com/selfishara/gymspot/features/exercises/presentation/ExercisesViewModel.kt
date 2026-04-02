package com.selfishara.gymspot.features.exercises.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.data.repository.ExercisesRepositoryImpl
import com.selfishara.gymspot.domain.usecase.GetExercisesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the exercises screen.
 *
 * Responsible for fetching exercises from the API and exposing
 * the corresponding UI state to the presentation layer.
 */
class ExercisesViewModel : ViewModel() {

    private val getExercisesUseCase = GetExercisesUseCase(ExercisesRepositoryImpl())

    private val _uiState = MutableStateFlow(ExercisesUiState())
    val uiState: StateFlow<ExercisesUiState> = _uiState.asStateFlow()

    init {
        loadExercises()
    }

    /**
     * Fetches exercises from the remote data source.
     */
    fun loadExercises() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            when (val result = getExercisesUseCase()) {
                is ResultWrapper.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        exercises = result.data,
                        errorMessage = null
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