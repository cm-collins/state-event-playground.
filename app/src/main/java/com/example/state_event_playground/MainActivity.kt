package com.example.state_event_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.state_event_playground.core.ui.theme.StateeventplaygroundTheme
import com.example.state_event_playground.playground.basics.counter.CounterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Call once here (don’t call it again inside setContent)
        enableEdgeToEdge()

        setContent {
            StateeventplaygroundTheme {
                Surface(modifier = Modifier) {
                    CounterScreen()
                }
            }
        }
    }
}