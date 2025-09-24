package isel.tds

import isel.tds.isel.tds.Date
import isel.tds.isel.tds.addDays
import isel.tds.isel.tds.isLastDayOfMonth
import isel.tds.isel.tds.isLeapYear
import isel.tds.isel.tds.plus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue



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

    @Test
    fun `test date creation with invalid days`(){
        assertFailsWith<IllegalArgumentException> {Date( 2025, 2, 29)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 1, 32)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 4, 31)}
    }

    @Test
    fun `test date creation with month greater than 12`(){
        assertFailsWith<IllegalArgumentException> {Date(2025, 13)}
        assertFailsWith<IllegalArgumentException> {Date(2025, 13, 3)}
    }

    @Test
    fun `test date is leap year`(){
        val date = Date(2024, 1,1)
        assertTrue( date.isLeapYear)

        val dateLeapYear400Multiple = Date(2400, 1,1)
        assertTrue( dateLeapYear400Multiple.isLeapYear)
    }

    @Test
    fun `test date isn't leap year`(){
        val date = Date(2025, 1,1)
        assertFalse( date.isLeapYear)

        val dateNotLeapYear100Multiple = Date(2100, 1,1)
        assertFalse( dateNotLeapYear100Multiple.isLeapYear)
    }

    @Test
    fun `test date is last day of month true`(){
        val dateLastDayOfJan = Date(2024, 1,31)
        assertTrue( dateLastDayOfJan.isLastDayOfMonth)

        val dateLastDayOfMonthInLeapYearForFeb = Date(2024, 2,29)
        assertTrue( dateLastDayOfMonthInLeapYearForFeb.isLastDayOfMonth)

        val dateLastDayOfMonthInNonLeapYearForFeb = Date(2025, 2,28)
        assertTrue( dateLastDayOfMonthInNonLeapYearForFeb.isLastDayOfMonth)
    }

    @Test
    fun `test date is last day of month false`(){
        val dateLastDayOfJan = Date(2024, 1,30)
        assertFalse( dateLastDayOfJan.isLastDayOfMonth)

        val dateLastDayOfMonthInLeapYearForFeb = Date(2024, 2,28)
        assertFalse( dateLastDayOfMonthInLeapYearForFeb.isLastDayOfMonth)

        val dateLastDayOfMonthInNonLeapYearForFeb = Date(2025, 2,27)
        assertFalse( dateLastDayOfMonthInNonLeapYearForFeb.isLastDayOfMonth)
    }

    @Test
    fun `test add days`(){
        val dateAdd = Date(2024, 1,1).addDays(5)
        assertEquals(6, dateAdd.day)

        val dateAddUsingOperator = Date(2024, 1,1) + 5
        assertEquals(6, dateAddUsingOperator.day)
    }
}


