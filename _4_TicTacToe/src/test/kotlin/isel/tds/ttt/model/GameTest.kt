package isel.tds.ttt.model

import isel.tds.ttt.model.Player.O
import isel.tds.ttt.model.Player.X
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameTest {
    @Test
    fun `test game init`(){
        val game = Game()
        assertTrue( game.gameState is Run)
        assertEquals(X, game.gameState.turn)
        assertTrue(game.board.all{ cellContent-> cellContent == EMPTY})
    }

    @Test
    fun `test game custom state`(){
        val board : Board = mapOf(
            (1.toPosition() to O),(2.toPosition() to X),
            (4.toPosition() to O),(5.toPosition() to X),
            (8.toPosition() to X)
        )
        assertTrue( board.winnerIn(8.toPosition()))
        assertFalse( board.winnerIn(4.toPosition()))

    }

    @Test
    fun `test game Winner over custom state`() {
        val board : Board = mapOf(
            (1.toPosition() to O),(2.toPosition() to X),
            (4.toPosition() to O),(5.toPosition() to X)
        )
        val game = Game(
            gameState = Run(turn = X), board = board
        )
        assertTrue( game.canPlay( 8.toPosition()) )
        val newGame = game.play(8.toPosition())

        assertTrue ( newGame.gameState is Win)
        assertEquals(X, newGame.gameState.winner)
    }
}