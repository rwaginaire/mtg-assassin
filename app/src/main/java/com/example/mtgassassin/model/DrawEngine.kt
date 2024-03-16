package com.example.mtgassassin.model

class DrawEngine {
    private lateinit var _players: Array<Player>
    val numberOfPlayer: Int
        get() = _players.size

    val players: List<Player>
        get() = _players.toList()

    fun savePlayers(players: List<Player>) {
        _players = players.toTypedArray()
    }

    fun drawTargets() {
        val playersWithoutTarget = _players.indices.toMutableSet()
        val playersRemainingToBeDrawn = _players.indices.toMutableSet()
        var currentPlayerId: Int
        var targetId: Int
        while (playersWithoutTarget.isNotEmpty()) {
            currentPlayerId = getNextPlayerWithoutTarget(playersWithoutTarget, playersRemainingToBeDrawn)
            targetId = drawTargetForPlayer(currentPlayerId, playersRemainingToBeDrawn)
            _players[currentPlayerId].target = _players[targetId].name
            playersWithoutTarget.remove(currentPlayerId)
            playersRemainingToBeDrawn.remove(targetId)
        }
    }

    private fun drawTargetForPlayer(
        playerId: Int,
        possibleTargets: Set<Int>
    ): Int {

        return (possibleTargets - playerId).random()
    }

    private fun getNextPlayerWithoutTarget(
        playersWithoutTarget: Set<Int>,
        playersRemainingToBeDrawn: Set<Int>
    ): Int {
        return getCriticalPlayer(playersWithoutTarget, playersRemainingToBeDrawn) ?: playersWithoutTarget.random()
    }

    private fun getCriticalPlayer(
        playersWithoutTarget: Set<Int>,
        playersRemainingToBeDrawn: Set<Int>
    ) : Int? {
        for (player in playersWithoutTarget) {
            if ((playersRemainingToBeDrawn - player).size == 1) {
                return player
            }
        }
        return null
    }
}