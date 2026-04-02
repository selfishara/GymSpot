package com.selfishara.gymspot.domain.repository

import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.domain.model.Exercise

/**
 * Repository contract for exercise-related operations.
 */
interface ExercisesRepository {

    /**
     * Retrieves the list of exercises from the remote data source.
     *
     * @return Result containing the list of exercises or an error.
     */
    suspend fun getExercises(): ResultWrapper<List<Exercise>>
}