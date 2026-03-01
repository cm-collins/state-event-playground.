package com.example.state_event_playground.playground.basics.counter

/**
 * UI EVENTS (things that happened).
 *
 * Why sealed interface?
 * - The compiler knows all possible events.
 * - Your when(event) becomes exhaustive (safer refactors).
 *
 * Why not pass lambdas everywhere?
 * - Lambdas are fine for tiny demos, but sealed events scale better:
 *   you keep all actions documented and handled in one place.
 */

sealed interface  CounterEvent  {
    data object  IncrementClicked: CounterEvent
    data object  DecrementClicked: CounterEvent
    data  object  ResetClicked: CounterEvent
}