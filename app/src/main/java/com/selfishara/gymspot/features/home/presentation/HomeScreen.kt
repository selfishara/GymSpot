package com.selfishara.gymspot.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.selfishara.gymspot.core.ui.components.base.AppButton
import com.selfishara.gymspot.core.ui.components.base.AppCard

/**
 * Home screen of the GymSpot application.
 *
 * This screen acts as the main dashboard of the app and provides
 * quick access to key features such as workouts, routines, and gyms.
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeHeader()
        MainWorkoutCard()
        QuickActionsSection()
        ProgressSummarySection()
    }
}

/**
 * Displays the header section of the home screen with a welcome message.
 *
 * This section greets the user and sets a positive tone for their workout session.
 */
@Composable
private fun HomeHeader() {
    Column {
        Text(
            text = "Welcome back",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Ready for today’s workout?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Displays the main workout card on the home screen.
 *
 * This card encourages users to start their training session and provides
 * a quick overview of the workout tracking features available in the app.
 */
@Composable
private fun MainWorkoutCard() {
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Start your training session",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Track your workout, log your sets, and keep your progress up to date.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            AppButton(
                text = "Start workout",
                onClick = {}
            )
        }
    }
}

/**
 * Displays a section with quick action cards for common tasks.
 *
 * This section provides shortcuts to key features of the app, allowing users
 * to quickly navigate to exercises, routines, gyms, and their profile.
 */
@Composable
private fun QuickActionsSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Quick actions",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Exercises",
                subtitle = "Browse exercises"
            )

            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Routines",
                subtitle = "View your plans"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Gyms",
                subtitle = "Find nearby gyms"
            )

            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Profile",
                subtitle = "Check your progress"
            )
        }
    }
}
/**
 * Displays a card for a quick action on the home screen.
 *
 * @param title The main title of the action (e.g., "Exercises")
 * @param subtitle A brief description of the action (e.g., "Browse exercises")
 * @param modifier Modifier for styling and layout
 */
@Composable
private fun QuickActionCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Displays a summary of the user's workout progress.
 *
 * This section provides quick insights into the user's activity, such as
 * the number of workouts completed in the current week, their favorite routine,
 * and details about their last workout session.
 */
@Composable
private fun ProgressSummarySection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Progress summary",
            style = MaterialTheme.typography.titleLarge
        )

        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryItem(label = "Weekly workouts", value = "0")
                SummaryItem(label = "Favorite routine", value = "Not set")
                SummaryItem(label = "Last session", value = "No workouts yet")
            }
        }
    }
}

/**
 * Displays a single summary item with a label and value.
 *
 * @param label The description of the summary item (e.g., "Weekly workouts")
 * @param value The corresponding value for the summary item (e.g., "3")
 */
@Composable
private fun SummaryItem(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}