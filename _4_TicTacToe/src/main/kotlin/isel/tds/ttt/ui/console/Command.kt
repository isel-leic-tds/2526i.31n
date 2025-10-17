package isel.tds.ttt.ui.console

import isel.tds.ttt.model.BOARD_TOTAL_SIZE
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.play
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.model.toPositionOrNull
import kotlin.system.exitProcess


//abstract class Command(val commandHelpMsg: String) {
//    open fun execute(game: Game?,args: List<String>) = game
//}
//
//object Play: Command("Play <positionIndex> - plays the game in the position. ex: PLAY 0"){
//    override fun execute(game: Game?, args: List<String>): Game? {
//        val arg = requireNotNull(args.firstOrNull() ) { "Missing position" }
//        val pos = arg.toPosition()
////        return checkNotNull(game){"Game not started"}.play(pos)
//        checkNotNull(game){"Game not started"}
//        return game.play(pos)
//    }
//}
//
//object New: Command("NEW - creates a new game or restarts an ongoing one") {
//    override fun execute(game: Game?, args: List<String>): Game? =
//        if (game == null) Game() else game.restartGame()
//}
//
//object Score: Command("SCORE - shows the score") {
//    override fun execute(game: Game?, args: List<String>): Game? =
//        game.also { game -> game?.showScore() }
//}
//
//object Exit: Command("EXIT - Ends the world") {
//    override fun execute(game: Game?, args: List<String>): Game? {
//        exitProcess(0)
//    }
//}
//
//object Help: Command("Help - prints a list of commands") {
//    override fun execute(game: Game?, args: List<String>): Game? {
//        println("")
//        getAllCommands().forEach { _, cmd: Command -> println(cmd.commandHelpMsg) }
//        println("")
//        return game
//    }
//}

fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
    "NEW" to New,
    "HELP" to Help,
    "SCORE" to Score,
    "EXIT" to Exit
)

class Command(
    val commandHelpMsg: String = "",
    val execute: (args: List<String>, Game?) -> Game? = { _, g -> g },
)

private val Exit = Command(commandHelpMsg = "EXIT - exit the game")
{ _, _ -> exitProcess(0) }

private val Play = Command(  commandHelpMsg = "PLAY <position> - plays the game in the position. ex: PLAY 0")
    { args, game ->
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull())
        { "Invalid position $arg, must be in 0..${BOARD_TOTAL_SIZE -1}" }
        checkNotNull(game){"Before playing create a game using the command - New"}
        game.play(pos)
    }


private val Help = Command(  commandHelpMsg = "HELP - print the commands")
{ args, game ->
    println("")
    getAllCommands()
        .forEach { key: String, cmd: Command -> println(cmd.commandHelpMsg) }
    println("")
    game
}

private val New = Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game")
{ _, game ->  if(game==null) Game() else game.restartGame() }

private val Score = Command(  commandHelpMsg = "SCORE - shows the score")
{ _, game ->
    game.also { game -> game?.showScore() }
}