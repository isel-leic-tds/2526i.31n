package isel.tds.ttt.storage

import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Player.O
import isel.tds.ttt.model.Player.X
import isel.tds.ttt.model.Run
import isel.tds.ttt.model.toPosition
import kotlin.test.Test
import kotlin.test.assertEquals


class GameSerializerTest {
    @Test
    fun `test game serializer`(){
        val game = Game(
            currentGameStarterPlayer = X,
            board = mapOf(
                (1.toPosition() to O),(2.toPosition() to X),
                (4.toPosition() to O),(5.toPosition() to X),
                (8.toPosition() to X)
            ),
            gameState = Run(O),
            score = mapOf((X to 2), (O to 4), (null to 9))
        )

        val serializedGame = GameSerializer.serialize(game)

        val readGame = GameSerializer.deserialize(serializedGame)

        assertEquals(game, readGame)
    }
}