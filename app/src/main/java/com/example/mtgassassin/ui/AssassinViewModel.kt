package com.example.mtgassassin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mtgassassin.model.DrawEngine
import com.example.mtgassassin.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AssassinViewModel : ViewModel() {
    private val _inputPlayers = mutableStateListOf<Player>()
    private val drawEngine = DrawEngine()
    private val _uiState = MutableStateFlow(AssassinUiState())
    val uiState: StateFlow<AssassinUiState> = _uiState.asStateFlow()

    val inputPlayersList: List<Player>
        get() = _inputPlayers

    val finalPlayers: List<Player>
        get() = drawEngine.players

    var inputName by mutableStateOf("")
        private set

    fun removePlayer(player: Player) {
        _inputPlayers.remove(player)
    }

    fun addPlayer() {
        if (inputName.isNotBlank()) {
            _inputPlayers.add(Player(inputName.trim()))
            inputName = ""
            _uiState.update {
                it.copy(isNameEmpty = false)
            }
        }
        else {
            _uiState.update {
                it.copy(isNameEmpty = true)
            }
        }
    }

    fun updateInputName(name: String) {
        inputName = name
    }

    fun drawTargets() {
        drawEngine.savePlayers(_inputPlayers)
        drawEngine.drawTargets()
    }

    fun revealTarget() {
        _uiState.update {currentState ->
            currentState.copy(isTargetHidden = false)
        }
    }

    fun noMoreTargetsToReveal(): Boolean {
        return _uiState.value.currentPlayerCount == drawEngine.numberOfPlayer - 1
    }

    fun goToNextPlayer() {
        _uiState.update {
            it.copy(
                currentPlayerCount = it.currentPlayerCount + 1,
                isTargetHidden = true
            )
        }
    }

    fun throwPlayerNumberAlert() {
        _uiState.update {
            it.copy(
                notEnoughPlayers = true
            )
        }
    }

    fun dismissPlayerNumberAlert() {
        _uiState.update {
            it.copy(
                notEnoughPlayers = false
            )
        }
    }

    fun resetApp() {
        _inputPlayers.clear()
        _uiState.value = AssassinUiState()
    }
}