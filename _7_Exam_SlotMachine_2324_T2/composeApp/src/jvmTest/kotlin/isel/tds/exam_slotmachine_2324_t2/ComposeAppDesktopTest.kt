package isel.tds.exam_slotmachine_2324_t2

import isel.tds.exam_slotmachine_2324_t2.model.SlotState
import isel.tds.exam_slotmachine_2324_t2.model.isWinner
import org.junit.Assert
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ComposeAppDesktopTest {

    @Test
    fun isWinnerTrue() {
        val slotState = SlotState(1,1,1)
        assertTrue( slotState.isWinner() )
    }

    @Test
    fun isWinnerFalse() {
        val slotState = SlotState(1,3,1)
        assertFalse( slotState.isWinner() )
    }

    @Test
    fun testSlotStateWithInvalidByte(){
//        Assert.assertThrows<IllegalArgumentException>{SlotState(12,2,2)}
    }

    @Test
    fun testRandomSlotState(){
        val slotState1 = SlotState.random()
        Assert.assertTrue(slotState1.slot1 in 0..9)
        Assert.assertTrue(slotState1.slot2 in 0..9)
        Assert.assertTrue(slotState1.slot3 in 0..9)

        val slotState2 = SlotState.random()
        Assert.assertTrue(slotState2.slot1 in 0..9)
        Assert.assertTrue(slotState2.slot2 in 0..9)
        Assert.assertTrue(slotState2.slot3 in 0..9)

        Assert.assertNotEquals(slotState1, slotState2)
    }
}