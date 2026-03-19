package com.selfishara.gymspot.core.ui.components.base

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Reusable text field component.
 *
 * @param value Current text value
 * @param onValueChange Callback when text changes
 * @param label Label displayed inside the field
 * @param modifier Modifier for styling and layout
 */
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
    )
}