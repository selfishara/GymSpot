package com.selfishara.gymspot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfishara.gymspot.features.auth.presentation.RegisterScreen
import com.selfishara.gymspot.features.home.presentation.HomeScreen
import com.selfishara.gymspot.features.exercises.presentation.ExerciseScreen
import com.selfishara.gymspot.features.routines.presentation.RoutineScreen
import com.selfishara.gymspot.features.gyms.presentation.GymScreen
import com.selfishara.gymspot.features.profile.presentation.ProfileScreen
import com.selfishara.gymspot.features.auth.presentation.LoginScreen

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
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.HOME)
                {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen()
        }

        composable(Routes.HOME) {
            HomeScreen(
                onStartWorkoutClick = { navController.navigate(Routes.ROUTINES) },
                onExercisesClick = { navController.navigate(Routes.EXERCISES) },
                onRoutinesClick = { navController.navigate(Routes.ROUTINES) },
                onGymsClick = { navController.navigate(Routes.GYMS) },
                onProfileClick = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.EXERCISES) { ExerciseScreen() }
        composable(Routes.ROUTINES) { RoutineScreen() }
        composable(Routes.GYMS) { GymScreen() }
        composable(Routes.PROFILE) { ProfileScreen() }
    }
}