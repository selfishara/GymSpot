package com.selfishara.gymspot.core.ui.components.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Reusable primary button used across the application.
 *
 * @param text Text displayed inside the button
 * @param onClick Action triggered when the button is pressed
 * @param modifier Modifier for styling and layout
 * @param enabled Controls whether the button is clickable
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        Text(text = text)
    }
}
