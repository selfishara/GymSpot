package com.selfishara.gymspot.core.ui.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a card for a quick action on the home screen.
 *
 * @param title The main title of the action (e.g., "Exercises")
 * @param subtitle A brief description of the action (e.g., "Browse exercises")
 * @param onClick Action triggered when the card is pressed
 * @param modifier Modifier for styling and layout
 */
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = if (onClick != null){
            modifier.clickable { onClick() }
        } else {
            modifier
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}