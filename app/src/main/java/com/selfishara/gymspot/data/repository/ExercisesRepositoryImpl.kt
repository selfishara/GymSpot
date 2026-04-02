package com.selfishara.gymspot.data.repository

import com.selfishara.gymspot.core.network.HttpClientProvider
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.data.mapper.toDomain
import com.selfishara.gymspot.data.remote.dto.ExerciseListResponseDto
import com.selfishara.gymspot.domain.model.Exercise
import com.selfishara.gymspot.domain.repository.ExercisesRepository
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Implementation of [ExercisesRepository] using the remote API.
 */
class ExercisesRepositoryImpl : ExercisesRepository {

    override suspend fun getExercises(): ResultWrapper<List<Exercise>> {
        return try {
            val response: ExerciseListResponseDto =
                HttpClientProvider.client
                    .get("https://wger.de/api/v2/exerciseinfo/?limit=20")
                    .body()

            ResultWrapper.Success(response.results.map { it.toDomain() })
        } catch (exception: Exception) {
            ResultWrapper.Error(
                message = exception.message ?: "An unexpected error occurred while fetching exercises.",
                throwable = exception
            )
        }
    }
}