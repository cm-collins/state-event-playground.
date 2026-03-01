package com.example.state_event_playground.playground.basics.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * ViewModel:
 * - Owns state (StateFlow)
 * - Receives events (onEvent)
 * - Emits effects (SharedFlow)
 *
 * Why StateFlow (instead of mutableStateOf)?
 * - StateFlow is lifecycle-friendly, works great with coroutines, and scales well.
 * - It's perfect when state belongs to "business/UI logic layer" (ViewModel),
 *   not inside a single composable.
 *
 * Efficiency notes:
 * - StateFlow is "hot" and keeps the latest value.
 * - It is conflated: if updates happen fast, collectors get the latest state,
 *   which prevents wasting work.
 */


class CounterViewModel: ViewModel() {
    // Private mutable state (only ViewModel can change it)
    private val _state  = MutableStateFlow(CounterState())
    // Public read-only state (UI can observe but not modify)
    val state: StateFlow<CounterState> = _state.asStateFlow()

    /**
     * Effects are one-time signals.
     * SharedFlow is good for this because it is not "sticky" like StateFlow.
     */
    private val _effects = MutableSharedFlow<CounterEffect>()
    val effects: SharedFlow<CounterEffect> =  _effects.asSharedFlow()

    fun onEvent (event: CounterEvent) {
        when (event)
        {
            CounterEvent.IncrementClicked -> increment()
            CounterEvent.DecrementClicked -> decrement()
            CounterEvent.ResetClicked -> reset()
        }


    }

    private fun increment() {
        // update { current -> newState } is atomic and thread-safe for StateFlow
        _state.update { current ->
            val newCount = current.count + 1
            current.copy(count = newCount)
        }

        // Example: show a message at a milestone (one-time effect)
        val c = _state.value.count
        if (c == 10) {
            // tryEmit is non-suspending; good for simple UI effects.
            // If you need guaranteed delivery, emit from a coroutine.
            _effects.tryEmit(CounterEffect.ShowMessage("Reached 10 🎉"))
        }
    }

    private fun decrement() {
        _state.update { current ->
            val newCount = current.count - 1
            current.copy(count = newCount)
        }
    }

    private fun reset() {
        _state.update { it.copy(count = 0) }
        _effects.tryEmit(CounterEffect.ShowMessage("Reset to 0"))
    }



}
