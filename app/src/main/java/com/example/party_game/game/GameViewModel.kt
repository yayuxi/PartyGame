package com.example.party_game.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state.asStateFlow()

    fun nextStep() {
        _state.value = _state.value.copy(
            taskText = "Cut the red wire!",
            helpersText = "Tell them to cut the red wire!"
        )
    }
}
