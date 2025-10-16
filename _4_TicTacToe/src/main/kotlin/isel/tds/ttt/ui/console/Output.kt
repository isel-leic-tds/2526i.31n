package isel.tds.ttt.ui.console

import isel.tds.ttt.model.BOARD_SIDE_SIZE
import isel.tds.ttt.model.Draw
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Player
import isel.tds.ttt.model.Run
import isel.tds.ttt.model.Win
import isel.tds.ttt.model.isDraw
import isel.tds.ttt.model.isWinner

fun Game.show(){
    this.board.chunked(BOARD_SIDE_SIZE).forEachIndexed { rowIdx, rowList ->
        println(" "+rowList.map{p->p?.toString()?:" "}.joinToString(" | "))
        if( rowIdx <BOARD_SIDE_SIZE-1) println("---+---+---")
    }
    val messageToShow: String = when(gameState){
        is Win -> "Winner ${gameState.winner}"
        is Draw -> "Draw"
        is Run -> "turn: ${gameState.turn}"
    }
    println(messageToShow)
}