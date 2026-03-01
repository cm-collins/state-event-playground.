package com.example.state_event_playground.playground.basics.counter
/**
 * UI EFFECTS (one-time actions).
 *
 * Examples:
 * - Show a Snackbar/Toast
 * - Navigate to another screen
 *
 * Why not put these in CounterState?
 * - State is for "what UI looks like".
 * - Effects are "do this once".
 *
 * If you put effects in state, recomposition or configuration changes can trigger them again.
 */

sealed  interface CounterEffect {
    data class ShowMessage (val message: String): CounterEffect
}
