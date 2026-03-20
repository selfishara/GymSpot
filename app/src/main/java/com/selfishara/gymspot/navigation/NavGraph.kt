package com.selfishara.gymspot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfishara.gymspot.core.navigation.navigateSingleTopTo
import com.selfishara.gymspot.features.exercises.presentation.ExerciseScreen
import com.selfishara.gymspot.features.gyms.presentation.GymScreen
import com.selfishara.gymspot.features.home.presentation.HomeScreen
import com.selfishara.gymspot.features.profile.presentation.ProfileScreen
import com.selfishara.gymspot.features.routines.presentation.RoutineScreen

/**
 * Main navigation graph of the GymSpot application.
 *
 * This composable defines the top-level destinations of the app and
 * configures the start destination for the initial navigation flow.
 */
@Composable
fun GymSpotNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onStartWorkoutClick = {
                    navController.navigateSingleTopTo(Routes.ROUTINES)
                },
                onExercisesClick = {
                    navController.navigateSingleTopTo(Routes.EXERCISES)
                },
                onRoutinesClick = {
                    navController.navigateSingleTopTo(Routes.ROUTINES)
                },
                onGymsClick = {
                    navController.navigateSingleTopTo(Routes.GYMS)
                },
                onProfileClick = {
                    navController.navigateSingleTopTo(Routes.PROFILE)
                }
            )
        }

        composable(Routes.EXERCISES) {
            ExerciseScreen()
        }

        composable(Routes.ROUTINES) {
            RoutineScreen()
        }

        composable(Routes.GYMS) {
            GymScreen()
        }

        composable(Routes.PROFILE) {
            ProfileScreen()
        }
    }
}