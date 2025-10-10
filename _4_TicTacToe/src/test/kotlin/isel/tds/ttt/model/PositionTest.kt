package isel.tds.ttt.model

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
import kotlin.test.assertFailsWith

class PositionTest {
    @Test
    fun `test position creation`() {
        assertEquals(2, 2.toPosition().index)
        assertEquals(0, 0.toPosition().index)
        assertEquals(BOARD_TOTAL_SIZE-1, (BOARD_TOTAL_SIZE-1).toPosition().index)
        assertEquals(2, "2".toPosition().index)
        assertEquals(0, "0".toPosition().index)
        assertEquals(BOARD_TOTAL_SIZE-1, (BOARD_TOTAL_SIZE-1).toString().toPosition().index)
    }

    @Test
    fun `test invalid position creation`() {
        assertFailsWith<IllegalArgumentException> { (-1).toPosition() }
        assertFailsWith<IllegalArgumentException> { BOARD_TOTAL_SIZE.toPosition() }
        assertFailsWith<IllegalArgumentException> { "-1".toPosition() }
        assertFailsWith<IllegalArgumentException> { BOARD_TOTAL_SIZE.toString().toPosition() }
    }

    @Test
    fun `test unique position creation`() {
        assertTrue( 2.toPosition() === 2.toPosition())
    }
}