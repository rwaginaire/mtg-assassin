package com.example.mtgassassin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.mtgassassin.data.Player
import com.example.mtgassassin.utils.testPlayersList

class AssassinViewModel : ViewModel() {
    private val _players = mutableStateListOf<Player>()
    val playersList: List<Player>
        get() = _players

    var inputName by mutableStateOf("")
        private set

    fun removePlayer(player: Player) {
        _players.remove(player)
    }

    fun addPlayer() {
        _players.add(Player(inputName))
    }

    fun updateInputName(name: String){
        inputName = name
    }
}