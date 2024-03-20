package com.example.mtgassassin.ui

data class AssassinUiState(
    val currentPlayerCount: Int = 0,
    val isTargetHidden: Boolean = true,
    val isNameEmpty: Boolean = false,
    val notEnoughPlayers: Boolean = false
)