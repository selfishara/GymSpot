package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * DTO representing a paginated exercise response from the API.
 */
@Serializable
data class ExerciseListResponseDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<ExerciseDto>
)