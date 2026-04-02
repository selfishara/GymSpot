package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseImageDto(
    val id: Int,
    val image: String,
    val is_main: Boolean = false
)