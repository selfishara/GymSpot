package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseTranslationDto(
    val id: Int,
    val name: String,
    val description: String? = null,
    val language: Int
)