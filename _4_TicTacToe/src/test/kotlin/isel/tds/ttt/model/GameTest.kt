package isel.tds.ttt.model

import isel.tds.ttt.model.Player.O
import isel.tds.ttt.model.Player.X
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class GameTest {
    @Test
    fun `test game init`(){
        val game = Game()
        assertEquals(X, game.turn)
        assertTrue(game.board.all{ cellContent-> cellContent == EMPTY})
    }

    @Test
    fun `test game custom state`(){
        val game = Game(turn= O, board = listOf(
            EMPTY, O, X,
            EMPTY, O, X,
            EMPTY, EMPTY, X
        ))

        assertTrue( game.isWinner(X))

    }
}