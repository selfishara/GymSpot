package com.selfishara.gymspot.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfishara.gymspot.features.auth.presentation.AppStartState
import com.selfishara.gymspot.features.auth.presentation.LoginScreen
import com.selfishara.gymspot.features.auth.presentation.RegisterScreen
import com.selfishara.gymspot.features.auth.presentation.rememberAppStartState
import com.selfishara.gymspot.features.exercises.presentation.ExerciseScreen
import com.selfishara.gymspot.features.gyms.presentation.GymScreen
import com.selfishara.gymspot.features.home.presentation.HomeScreen
import com.selfishara.gymspot.features.profile.presentation.ProfileScreen
import com.selfishara.gymspot.features.routines.presentation.RoutineScreen

/**
 * Main navigation graph of the GymSpot application.
 *
 * This composable decides the initial navigation flow depending
 * on the authentication state of the user.
 */
@Composable
fun GymSpotNavGraph() {
    val navController = rememberNavController()
    val appStartState = rememberAppStartState().value

    when (appStartState) {
        AppStartState.Loading -> {
            Text("Loading...")
        }

        AppStartState.Authenticated -> {
            NavHost(
                navController = navController,
                startDestination = Routes.EXERCISES
            ) {
                composable(Routes.HOME) {
                    HomeScreen(
                        onStartWorkoutClick = { navController.navigate(Routes.ROUTINES) },
                        onExercisesClick = { navController.navigate(Routes.EXERCISES) },
                        onRoutinesClick = { navController.navigate(Routes.ROUTINES) },
                        onGymsClick = { navController.navigate(Routes.GYMS) },
                        onProfileClick = { navController.navigate(Routes.PROFILE) }
                    )
                }

                composable(Routes.LOGIN) {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Routes.REGISTER) {
                    RegisterScreen()
                }

                composable(Routes.EXERCISES) { ExerciseScreen() }
                composable(Routes.ROUTINES) { RoutineScreen() }
                composable(Routes.GYMS) { GymScreen() }
                composable(Routes.PROFILE) { ProfileScreen() }
            }
        }

        AppStartState.Unauthenticated -> {
            NavHost(
                navController = navController,
                startDestination = Routes.LOGIN
            ) {
                composable(Routes.LOGIN) {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate(Routes.HOME) {
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
    }
}