package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * DTO representing an exercise returned by the remote API.
 */
@Serializable
data class ExerciseDto(
    val id: Int,
    val category: ExerciseCategoryDto? = null,
    val muscles: List<ExerciseMuscleDto> = emptyList(),
    val muscles_secondary: List<ExerciseMuscleDto> = emptyList(),
    val equipment: List<ExerciseEquipmentDto> = emptyList(),
    val images: List<ExerciseImageDto> = emptyList(),
    val translations: List<ExerciseTranslationDto> = emptyList()
)