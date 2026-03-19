package com.selfishara.gymspot.features.home.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.selfishara.gymspot.core.ui.components.base.AppButton

/**
 * Home screen of the GymSpot application.
 *
 * This screen will serve as the main dashboard of the app.
 * In future iterations it will display:
 *
 * - Quick access to routines
 * - Recent workouts
 * - Suggested exercises
 * - User progress highlights
 */
@Composable
fun HomeScreen() {
    Text(text = "Welcome to the Home Screen!")

    AppButton(
        text = "Go to Routines",
        onClick = { /* TODO: Navigate to Routines screen */ }
    )
}