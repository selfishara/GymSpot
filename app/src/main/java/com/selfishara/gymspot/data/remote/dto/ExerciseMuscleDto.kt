package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseMuscleDto(
    val id: Int,
    val name: String,
    val name_en: String? = null
)