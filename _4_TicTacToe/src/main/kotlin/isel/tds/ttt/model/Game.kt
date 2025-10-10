package isel.tds.ttt.model

import isel.tds.ttt.model.Player.X

val EMPTY = null
const val BOARD_SIDE_SIZE = 3
const val BOARD_TOTAL_SIZE = BOARD_SIDE_SIZE * BOARD_SIDE_SIZE

data class Game(
    val currentGameStarterPlayer: Player = X,
    val turn: Player = currentGameStarterPlayer,
    val board: List<Player?> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY
    )
)

fun Game.play(pos: Position): Game {
    val newBoard = board.mapIndexed { idx, cellContent ->
        if (idx == pos.index) turn
        else cellContent
    }
    return this.copy( turn = turn.other(), board = newBoard)
}

fun Game.canPlay(pos: Position): Boolean = board[pos.index] == EMPTY


fun Game.restartGame(): Game = Game( currentGameStarterPlayer.other())

fun Game.isWinner(p: Player): Boolean =
    //check Rows
    (0..6 step 3).any{ rowIdx-> (0..2).all{ offset -> board[rowIdx+offset] == p}} ||
    //check Colums
    (0..2).any{ colIdx -> (0..6 step 3).all { offset -> board[colIdx+offset] == p }} ||
    // check diagonal \
    (0..8 step 4).all{ pos -> board[pos] == p} ||
    // check diagonal /
    (2..6 step 2).all { pos -> board[pos] == p}

/**
 * IsDraw is called on a fully filled board only when there are no winners,
 * or when the board is not full and no winners
 */

fun Game.isDraw(): Boolean = board.none { elem -> elem == EMPTY }

