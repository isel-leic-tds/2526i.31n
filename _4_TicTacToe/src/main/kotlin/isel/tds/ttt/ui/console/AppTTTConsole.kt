package isel.tds.ttt.ui.console

import isel.tds.ttt.model.Clash
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Name
import isel.tds.ttt.storage.GameSerializer
import isel.tds.ttt.storage.TextFileStorage
import isel.tds.ttt.storage.mongo.MongoDriver
import isel.tds.ttt.storage.mongo.MongoStorage

object AppTTTConsole {
// We can have an alternative solution using the companion object
//class AppTTTConsole {

//    companion object {
    fun run() {
        MongoDriver("JogoGalo31N").use { driver ->
            val st = MongoStorage<Name, Game>("savedGames", driver, GameSerializer)

            //        val st = TextFileStorage<Name, Game>("savedGames", GameSerializer)
            var clash: Clash = Clash(st)

            val commands: Map<String, Command> = getAllCommands()
            while (true) {
                try {
                    print("$ ")
                    val (cmdStr, args) = readCommand()
                    val command: Command? = commands[cmdStr]
                    if (command != null) {
                        clash = with(command) { clash.execute(args) }
                    } else {
                        println("Invalid command $cmdStr")
                    }
                    clash.show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error occurred: ${e.message}")
                }
            }
        }
    }
}