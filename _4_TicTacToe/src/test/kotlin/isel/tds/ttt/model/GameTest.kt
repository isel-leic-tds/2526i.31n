package isel.tds.ttt.model

import isel.tds.ttt.model.Player.O
import isel.tds.ttt.model.Player.X
import kotlin.test.Test
import kotlin.test.assertEquals
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
        val game = Game(gameState = Run(turn= O), board = listOf(
            EMPTY, O, X,
            EMPTY, O, X,
            EMPTY, EMPTY, X
        ))

        assertTrue( game.isWinner(X))

    }

    @Test
    fun `test game Winner over custom state`() {
        val game = Game(
            gameState = Run(turn = X), board = listOf(
                EMPTY, O, X,
                EMPTY, O, X,
                EMPTY, EMPTY, EMPTY
            )
        )
        val newGame = game.play(8.toPosition())

        assertTrue ( newGame.gameState is Win )
        assertEquals(Player.X, newGame.gameState.winner)
    }
}