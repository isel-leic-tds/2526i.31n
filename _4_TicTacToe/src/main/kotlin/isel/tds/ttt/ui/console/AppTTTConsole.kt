package isel.tds.ttt.ui.console

import isel.tds.ttt.model.Game
import isel.tds.ttt.model.canPlay
import isel.tds.ttt.model.play
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.model.toPosition

object AppTTTConsole {
// We can have an alternative solution using the companion object
//class AppTTTConsole {

//    companion object {
        fun run() {
            var game: Game? = null
            val commands: Map<String, Command> = getAllCommands()
            while (true) {
                try {
                    print("$ ")
                    val (cmdStr, args) = readCommand()
                    val command: Command? = commands[cmdStr]
                    if( command!=null ) {
                        game = command.execute(game, args)
                    }else{
                        println("Invalid command $cmdStr")
                    }
//                    when (input[0]) {
//                        "NEW" ->
//                            if (game == null) game = Game()
//                            else game = game.restartGame()
//
//                        "PLAY" -> {
//                            checkNotNull(game) { "Game not started" }
//                            //                    val pos = input[1].toInt()
//                            val pos = input[1].toPosition()
//                            if (game.canPlay(pos))
//                                game = game.play(pos)
//                            else println("play not valid")
//                        }
//
//                        "EXIT" -> break
//                        else -> println("Invalid command")
//                    }
                    game?.show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error occurred: ${e.message}")
                }
            }
        }
//    }
}