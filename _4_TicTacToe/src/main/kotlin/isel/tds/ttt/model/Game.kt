package isel.tds.ttt.model

private const val EMPTY = ' '
const val BOARD_SIZE = 3

data class Game(
    val nextGamePlayer: Char = 'X',
    val turn: Char = nextGamePlayer,
    val board: List<Char> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY
    )
)

fun Game.play(pos: Int): Game {
    val newBoard = board.mapIndexed { idx, cellContent ->
        if (idx == pos) turn
        else cellContent
    }
    return this.copy( turn = turn.other(), board = newBoard)
}

fun Game.canPlay(pos: Int): Boolean = board[pos] == EMPTY
private fun Char.other(): Char = if (this == 'X') 'O' else 'X'

fun Game.restartGame(): Game = Game( nextGamePlayer.other())