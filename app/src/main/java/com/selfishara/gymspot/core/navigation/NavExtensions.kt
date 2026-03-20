package com.selfishara.gymspot.core.navigation

import androidx.navigation.NavController

/**
 * Navigates to a top-level destination while avoiding duplicate destinations
 * in the back stack and restoring previous state when possible.
 *
 * This helper is intended for main navigation actions such as switching
 * between primary app sections.
 *
 * @param route Destination route to navigate to
 */
fun NavController.navigateSingleTopTo(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId)
        launchSingleTop = true
        restoreState = true
    }
}