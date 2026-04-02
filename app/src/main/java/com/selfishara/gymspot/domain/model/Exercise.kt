package com.selfishara.gymspot.domain.model

/**
 * Domain model representing an exercise in the application.
 */
data class Exercise(
    val id: Int,
    val name: String,
    val description : String,
    val category: String,
    val equipment: List<String>,
    val primaryMuscles: List<String>,
    val secondaryMuscles: List<String>,
    val imageUrl: String? = null
)
