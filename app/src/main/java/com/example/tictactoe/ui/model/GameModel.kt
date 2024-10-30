package com.example.tictactoe.ui.model

import com.example.tictactoe.data.network.model.GameData
import com.example.tictactoe.data.network.model.PlayerData

data class GameModel(
    val board: List<PlayerType>,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel,
    val gameId: String,
    val isGameReady: Boolean = false,
    val isMyTurn:Boolean = false
) {
    fun toData(): GameData {
        return GameData(
            board = board.map { it.id },
            gameId = gameId,
            player1 = player1.toData(),
            player2 = player2?.toData(),
            playerTurn = playerTurn.toData()
        )
    }
}

sealed class PlayerType(val id: Int, val symbol: String) {
    object FirstPlayer : PlayerType(2, "X")
    object SecondPlayer : PlayerType(3, "O")
    object Empty : PlayerType(0, "")

    companion object {
        fun getPlayerById(id: Int?): PlayerType {
            return when (id) {
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }
        }
    }

}

data class PlayerModel(val userId: String, val playerType: PlayerType) {
    fun toData(): PlayerData {
        return PlayerData(
            userId = userId,
            playerType = playerType.id
        )
    }
}