package isel.tds.ttt

import isel.tds.ttt.model.Game
import isel.tds.ttt.model.canPlay
import isel.tds.ttt.model.play
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.ui.console.show

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var game: Game? = null

    while(true) {
        try {
            print("$ ")
            val input = readln().uppercase().split(' ')
            when (input[0]) {
                "NEW" ->
                    if(game==null) game = Game()
                    else game = game.restartGame()
                "PLAY" -> {
                    checkNotNull(game) { "Game not started" }
                    val pos = input[1].toInt()
                    if( game.canPlay(pos))
                        game = game.play(pos)
                    else println("play not valid")
                }

                "EXIT" -> break
                else -> println("Invalid command")
            }
            game?.show()
        }catch(e:Exception){
            println("Error occurred: ${e.message}")
        }
    }
}


