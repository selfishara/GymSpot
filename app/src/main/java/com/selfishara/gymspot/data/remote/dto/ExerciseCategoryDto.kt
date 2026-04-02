package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseCategoryDto(
    val id: Int,
    val name: String
)