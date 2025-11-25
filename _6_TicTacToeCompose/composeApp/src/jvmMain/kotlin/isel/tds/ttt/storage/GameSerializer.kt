package isel.tds.ttt.storage

import isel.tds.ttt.model.Board
import isel.tds.ttt.model.Draw
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.GameState
import isel.tds.ttt.model.Run
import isel.tds.ttt.model.Score
import isel.tds.ttt.model.Win
import isel.tds.ttt.model.toPlayer
import isel.tds.ttt.model.toPlayerOrNull
import isel.tds.ttt.model.toPosition
import kotlin.collections.component1
import kotlin.collections.component2

object GameSerializer : Serializer<Game> {
    override fun serialize(game: Game): String = buildString{
        appendLine(game.currentGameStarterPlayer.name)
        appendLine(BoardSerializer.serialize(game.board))
        appendLine(GameStateSerializer.serialize(game.gameState))
        appendLine(GameScoreSerializer.serialize(game.score))
    }

    override fun deserialize(txt: String): Game =
        txt.split('\n').let{ (l1, l2, l3, l4)->
            Game(
                currentGameStarterPlayer = l1.toPlayer(),
                board = BoardSerializer.deserialize(l2),
                gameState = GameStateSerializer.deserialize(l3),
                score = GameScoreSerializer.deserialize(l4)
            )
        }
}



object BoardSerializer : Serializer<Board> {
    override fun serialize(board: Board): String {
        return board.entries.joinToString(" "){
                (pos, player) -> "${pos.index}:${player.name}"
        }
    }

    override fun deserialize(l2: String): Board {
        return if( l2.isBlank()) emptyMap()
        else l2.split(' ')
            .map{ boardEntry -> boardEntry.split(":")}
            .associate { (pos, player)-> ( pos.toPosition() to player.toPlayer()) }
    }
}

object GameStateSerializer : Serializer<GameState> {
    override fun serialize(gameState: GameState): String = when(gameState){
        is Run -> "Run:${gameState.turn}"
        is Win -> "Win:${gameState.winner}"
        is Draw -> "Draw:"
    }

    override fun deserialize(txt: String): GameState =
        txt.split(":").let { (type, player)->
            when(type){
                "Run"-> Run(player.toPlayer())
                "Win"->Win(player.toPlayer())
                "Draw"->Draw
                else -> error("Unknown game state: $type")
            }
        }
}

object GameScoreSerializer : Serializer<Score> {
    override fun serialize(score: Score): String {
        return score.entries.joinToString(" "){
                (player, score) -> "${player?.name ?: "null"}=${score}"
        }
    }

    override fun deserialize(l4: String): Score =
        l4.split(' ')
            .map{ scoreEntry -> scoreEntry.split("=")}
            .associate { (player, score)-> ( player.toPlayerOrNull() to score.toInt()) }
}