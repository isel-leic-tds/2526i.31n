package isel.tds.ttt.model

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PlayerTest {
    @Test
    fun `test other function`() {
        assertEquals(Player.X,Player.O.other())
        assertEquals(Player.O,Player.X.other())
        assertEquals(Player.O,Player.O.other().other())
    }

}