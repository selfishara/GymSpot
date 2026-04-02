package com.selfishara.gymspot.domain.usecase

import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.domain.model.Exercise
import com.selfishara.gymspot.domain.repository.ExercisesRepository

/**
 * Use case responsible for fetching the exercise list.
 */
class GetExercisesUseCase(
    private val exercisesRepository: ExercisesRepository
) {
    suspend operator fun invoke(): ResultWrapper<List<Exercise>> {
        return exercisesRepository.getExercises()
    }
}