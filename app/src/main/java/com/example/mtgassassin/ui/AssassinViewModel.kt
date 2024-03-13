package com.example.mtgassassin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mtgassassin.model.DrawEngine
import com.example.mtgassassin.model.Player

class AssassinViewModel : ViewModel() {
    private val _inputPlayers = mutableStateListOf<Player>()
    private val drawEngine = DrawEngine()
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
        _inputPlayers.add(Player(inputName))
        inputName = ""
    }

    fun updateInputName(name: String) {
        inputName = name
    }

    fun drawTargets() {
        drawEngine.savePlayers(_inputPlayers)
        drawEngine.drawTargets()
    }
}