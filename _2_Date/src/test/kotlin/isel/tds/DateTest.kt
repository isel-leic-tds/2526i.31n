package isel.tds

import isel.tds.isel.tds.Date
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
import kotlin.test.assertFailsWith

class DateTest {

    @Test
    fun testValidDateCreation() {
        var d = Date(2025, 11, 23)
        assertEquals(2025, d.year)
        assertEquals(11, d.month)
        assertEquals(23, d.day)
    }

    @Test
    fun `test valid date with default values`(){
        val dateWithDefaultDay = Date(2025, 11)
        assertEquals(2025, dateWithDefaultDay.year)
        assertEquals(11, dateWithDefaultDay.month)
        assertEquals(1, dateWithDefaultDay.day)

        val dateWithDefaultMonth = Date(2025)
        assertEquals(2025, dateWithDefaultMonth.year)
        assertEquals(1, dateWithDefaultMonth.month)
        assertEquals(1, dateWithDefaultMonth.day)
    }

    @Test
    fun `test date creation with negative values`(){
        assertFailsWith<IllegalArgumentException> {Date( -1)}
        assertFailsWith<IllegalArgumentException> {Date(2025, -1)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 1, -1)}

    }

    @Test
    fun `test date creation with 0 values`(){
        assertFailsWith<IllegalArgumentException> {Date( 0)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 0)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 1, 0)}

    }
}