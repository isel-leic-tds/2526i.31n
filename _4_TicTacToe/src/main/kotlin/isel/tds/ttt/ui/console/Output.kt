package isel.tds.ttt.ui.console

import isel.tds.ttt.model.BOARD_SIDE_SIZE
import isel.tds.ttt.model.Clash
import isel.tds.ttt.model.ClashRun
import isel.tds.ttt.model.Draw
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Player
import isel.tds.ttt.model.Position
import isel.tds.ttt.model.Run
import isel.tds.ttt.model.Win

private val separator = "---+".repeat(BOARD_SIDE_SIZE-1)+"---"
fun Game.show(){
//    this.board.chunked(BOARD_SIDE_SIZE).forEachIndexed { rowIdx, rowList ->
//        println(" "+rowList.map{p->p?.toString()?:" "}.joinToString(" | "))
//        if( rowIdx <BOARD_SIDE_SIZE-1) println("---+---+---")
//    }
//    val messageToShow: String = when(gameState){
//        is Win -> "Winner ${gameState.winner}"
//        is Draw -> "Draw"
//        is Run -> "turn: ${gameState.turn}"
//    }
//    println(messageToShow)
    Position.positionValues.chunked(BOARD_SIDE_SIZE).forEach { line ->
        val plays = line.map { pos -> board[pos]?.name ?: " " }
        println(plays.joinToString(separator = " | ", prefix = " "))
        if (line.first().row < BOARD_SIDE_SIZE - 1) println(separator)
    }
    // Show the game status
    println( when(gameState) {
        is Win -> "Winner: ${gameState.winner}"
        is Draw -> "Draw"
        is Run -> "Turn: ${gameState.turn}"
    })
}

fun Game.showScore() {
    score.entries.joinToString(",", "Scores:") { (player,points) ->
        " ${player?:"Draws"}=$points"
    }.let(::println)
}

fun Clash.show(){
    if (this is ClashRun) {
        println("Clash: $name Player: $sidePlayer")
        game.show()
    }
    else println("Clash not started")
}