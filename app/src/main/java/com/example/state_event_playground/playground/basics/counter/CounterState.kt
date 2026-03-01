package com.example.state_event_playground.playground.basics.counter

/**
 * UI STATE (what the screen should look like right now).
 *
 * Why a data class?
 * - Immutable by default (val fields), so state changes are explicit.
 * - copy(...) makes updates easy and safe.
 * - Immutable state is easier to debug and test than mutable scattered variables.
 *
 * Efficiency note:
 * - Compose likes immutable state objects because it can reason about changes better.
 * - Only the parts of the UI that read changed values will recompose.
 */

data class  CounterState (
    val count: Int = 0
)
