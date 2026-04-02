package com.selfishara.gymspot.data.mapper

import com.selfishara.gymspot.data.remote.dto.ExerciseDto
import com.selfishara.gymspot.domain.model.Exercise

private const val SPANISH_LANGUAGE_ID = 4

/**
 * Maps [ExerciseDto] objects into [Exercise] domain models.
 */
fun ExerciseDto.toDomain(): Exercise {
    val preferredTranslation = translations.firstOrNull { it.language == SPANISH_LANGUAGE_ID }
        ?: translations.firstOrNull()

    val mainImage = images.firstOrNull { it.is_main }?.image
        ?: images.firstOrNull()?.image

    return Exercise(
        id = id,
        name = preferredTranslation?.name.orEmpty(),
        description = preferredTranslation?.description.orEmpty(),
        category = category?.name.orEmpty(),
        equipment = equipment.map { it.name },
        primaryMuscles = muscles.map { it.name_en?.takeIf(String::isNotBlank) ?: it.name },
        secondaryMuscles = muscles_secondary.map { it.name_en?.takeIf(String::isNotBlank) ?: it.name },
        imageUrl = mainImage
    )
}