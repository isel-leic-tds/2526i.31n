package isel.tds.ttt.ui.console

import isel.tds.ttt.model.BOARD_TOTAL_SIZE
import isel.tds.ttt.model.Clash
import isel.tds.ttt.model.ClashRun
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Name
import isel.tds.ttt.model.join
import isel.tds.ttt.model.new
import isel.tds.ttt.model.play
import isel.tds.ttt.model.refresh
import isel.tds.ttt.model.restartGame
import isel.tds.ttt.model.start
import isel.tds.ttt.model.toPositionOrNull
import isel.tds.ttt.storage.Storage
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

private fun beginCommand(commandHelpMsg: String, exec: Clash.(Name) -> Clash) =
    Command(commandHelpMsg=commandHelpMsg) { args ->
        val word = requireNotNull(args.firstOrNull()) { "Missing name" }
        this.exec(Name(word))
    }

fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
//    "NEW" to New,
    "HELP" to Help,
    "SCORE" to Score,
    "START" to beginCommand("START <name> - Starts a game with the name ") { name -> start(name)},
    "JOIN" to beginCommand("JOIN <name> - Joins a game with the name ") { name ->join(name)},
    "REFRESH" to Command("REFRESH - Refresh the state of the game from storage"){ refresh()} ,
//    "SAVE" to Command("SAVE <name> - Save the game state to file"){
//        args, game ->
//        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
//        val isValidName = name.matches( Regex("[a-zA-Z][a-zA-Z0-9_]*") )
//        require(isValidName) { "Invalid name $name" }
//        game?.also{ st.create(name, game) }
//    },
//    "LOAD" to Command("LOAD <name> - Load the game from a file"){
//        args, _ ->
//        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
//        val isValidName = name.matches( Regex("[a-zA-Z][a-zA-Z0-9_]*") )
//        require(isValidName) { "Invalid name $name" }
//        checkNotNull(st.read(name)){"Game $name not found"}
//    },
    "EXIT" to Exit
)

class Command(
    val commandHelpMsg: String = "",
    val execute: Clash.(args: List<String>) -> Clash = { _ -> this },
)

private val Exit = Command(commandHelpMsg = "EXIT - exit the game")
{ _ -> exitProcess(0) }

private val Play = Command(  commandHelpMsg = "PLAY <position> - plays the game in the position. ex: PLAY 0")
    { args ->
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull())
        { "Invalid position $arg, must be in 0..${BOARD_TOTAL_SIZE -1}" }
        checkNotNull(this){"Before playing create a clash using the command - New"}
        play(pos)
    }

private val Help = Command(  commandHelpMsg = "HELP - print the commands")
{ _ ->
    println("")
    getAllCommands()
        .forEach { _: String, cmd: Command -> println(cmd.commandHelpMsg) }
    println("")
    this
}

private val New = Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game")
{ _ ->  new() }

private val Score = Command(  commandHelpMsg = "SCORE - shows the score")
{ _ ->
    this.also { clash -> (clash as ClashRun).game.showScore() }
}

//private val Save = Command(  commandHelpMsg = "Save <name> - Save's the game state to a file")
//{ _, game ->
//    st.create(name, game)
//}
//
//private val Load = Command(  commandHelpMsg = "Load <name> - Load's the game state to a file")
//{ _, game ->
//    return st.read(name)
//}
