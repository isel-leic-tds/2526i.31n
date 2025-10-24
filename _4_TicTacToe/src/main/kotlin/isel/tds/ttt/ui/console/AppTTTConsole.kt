package isel.tds.ttt.ui.console

import isel.tds.ttt.model.Game
import isel.tds.ttt.model.canPlay
import isel.tds.ttt.model.play
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.model.toPosition
import isel.tds.ttt.storage.GameSerializer
import isel.tds.ttt.storage.TextFileStorage

object AppTTTConsole {
// We can have an alternative solution using the companion object
//class AppTTTConsole {

//    companion object {
    fun run() {
        var game: Game? = null
        val st = TextFileStorage<String, Game>("savedGames", GameSerializer)
        val commands: Map<String, Command> = getAllCommands(st)
        while (true) {
            try {
                print("$ ")
                val (cmdStr, args) = readCommand()
                val command: Command? = commands[cmdStr]
                if( command!=null ) {
                    game = command.execute( args, game)
                }else{
                    println("Invalid command $cmdStr")
                }
                game?.show()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error occurred: ${e.message}")
            }
        }
    }
}