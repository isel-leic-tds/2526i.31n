package isel.tds.ttt.ui.console

import isel.tds.ttt.model.BOARD_SIZE
import isel.tds.ttt.model.Game

fun Game.show(){
    this.board.chunked(BOARD_SIZE).forEachIndexed { rowIdx, rowList ->
        println(rowList.joinToString(" | "))
        if( rowIdx <BOARD_SIZE-1) println("---+---+---")
    }
    println("turn: $turn")
}