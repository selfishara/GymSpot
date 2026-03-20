package com.selfishara.gymspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.selfishara.gymspot.core.ui.theme.GymSpotTheme
import com.selfishara.gymspot.core.navigation.GymSpotNavGraph
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymSpotTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    GymSpotNavGraph()
                }
            }
        }
    }
}
