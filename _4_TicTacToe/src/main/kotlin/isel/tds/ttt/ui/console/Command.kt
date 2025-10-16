package isel.tds.ttt.ui.console

import isel.tds.ttt.model.Game
import isel.tds.ttt.model.canPlay
import isel.tds.ttt.model.play
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.model.toPosition
import java.lang.System.exit
import kotlin.system.exitProcess


abstract class Command(val commandHelpMsg: String) {
    open fun execute(game: Game?,args: List<String>) = game
}

object Play: Command("Play <positionIndex> - plays the game in the position. ex: PLAY 0"){
    override fun execute(game: Game?, args: List<String>): Game? {
        val arg = requireNotNull(args.firstOrNull() ) { "Missing position" }
        val pos = arg.toPosition()
//        return checkNotNull(game){"Game not started"}.play(pos)
        checkNotNull(game){"Game not started"}
        return game.play(pos)
    }
}

object New: Command("NEW - creates a new game or restarts an ongoing one") {
    override fun execute(game: Game?, args: List<String>): Game? =
        if (game == null) Game() else game.restartGame()
}

object Exit: Command("EXIT - Ends the world") {
    override fun execute(game: Game?, args: List<String>): Game? {
        exitProcess(0)
    }
}

object Help: Command("Help - prints a list of commands") {
    override fun execute(game: Game?, args: List<String>): Game? {
        println("")
        getAllCommands().forEach { _, cmd: Command -> println(cmd.commandHelpMsg) }
        println("")
        return game
    }
}

fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
    "NEW" to New,
    "HELP" to Help,
    "EXIT" to Exit
)