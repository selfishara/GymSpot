package com.selfishara.gymspot.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseEquipmentDto(
    val id: Int,
    val name: String
)