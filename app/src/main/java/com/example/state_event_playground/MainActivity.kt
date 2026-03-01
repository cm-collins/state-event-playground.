package com.example.state_event_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.state_event_playground.core.ui.theme.StateeventplaygroundTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateeventplaygroundTheme {
                enableEdgeToEdge()
                 RootApp()
                }
            }
        }
    }



