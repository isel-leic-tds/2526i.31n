package isel.tds

import isel.tds.isel.tds.Date
import isel.tds.isel.tds.addDays
import isel.tds.isel.tds.compareTo
import isel.tds.isel.tds.isLastDayOfMonth
import isel.tds.isel.tds.isLeapYear
import isel.tds.isel.tds.plus
import kotlin.hashCode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.text.equals


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
        assertEquals(1, dateAdd.month)
        assertEquals(2024, dateAdd.year)

        val dateAddUsingOperator = Date(2024, 1,1) + 5
        assertEquals(6, dateAddUsingOperator.day)
        assertEquals(1, dateAddUsingOperator.month)
        assertEquals(2024, dateAddUsingOperator.year)
    }

    @Test
    fun `test add days that increment the month`(){
        val dateAdd = Date(2024, 1,31).addDays(1)
        assertEquals(1, dateAdd.day)
        assertEquals(2, dateAdd.month)
        assertEquals(2024, dateAdd.year)

        val dateAddUsingOperator = Date(2024, 1,31) + 1
        assertEquals(1, dateAddUsingOperator.day)
        assertEquals(2, dateAddUsingOperator.month)
        assertEquals(2024, dateAddUsingOperator.year)
    }

    @Test
    fun `test add days that increment the year`(){
        val dateAdd = Date(2024, 12,31).addDays(1)
        assertEquals(1, dateAdd.day)
        assertEquals(1, dateAdd.month)
        assertEquals(2025, dateAdd.year)

        val dateAddUsingOperator = Date(2024, 12,31) + 1
        assertEquals(1, dateAddUsingOperator.day)
        assertEquals(1, dateAddUsingOperator.month)
        assertEquals(2025, dateAddUsingOperator.year)
    }

    @Test
    fun `test add many days that increment the year`(){
        val dateAdd = Date(2024, 1,1).addDays(366)
        assertEquals(1, dateAdd.day)
        assertEquals(1, dateAdd.month)
        assertEquals(2025, dateAdd.year)

        val dateAddUsingOperator = Date(2024, 1,1) + 366
        assertEquals(1, dateAddUsingOperator.day)
        assertEquals(1, dateAddUsingOperator.month)
        assertEquals(2025, dateAddUsingOperator.year)
    }

    @Test
    fun `test add 0 days`(){
        val dateAdd = Date(2024, 1,31).addDays(0)
        assertEquals(31, dateAdd.day)
        assertEquals(1, dateAdd.month)
        assertEquals(2024, dateAdd.year)

        val dateAddUsingOperator = Date(2024, 1,31) + 0
        assertEquals(31, dateAddUsingOperator.day)
        assertEquals(1, dateAddUsingOperator.month)
        assertEquals(2024, dateAddUsingOperator.year)
    }

    @Test
    fun `test add days with Int first`(){
        val date = 5 + Date(2024, 2, 1)
        assertEquals(6, date.day)
    }


    @Test
    fun `test date equality`() {
        val date1 = Date(2024, 2, 1)
        val date2 = Date(2024, 2, 1)
        val date3 = Date(2024, 2, 2)

        assertTrue(date1 == date2)
        assertTrue(date1.equals(date2))
//        assertFalse(date1 === date2) //Not possible for value classes
        assertFalse(date1 == date3)
        assertFalse(date1.equals(date3))
//        assertFalse(date1 === date3) //Not possible for value classes
    }


    @Test
    fun `test date hashCode`() {
        val date1 = Date(2024, 2, 1)
        val date2 = Date(2024, 2, 1)
        val date3 = Date(2024, 2, 2)

        assertTrue(date1.hashCode().equals(date2.hashCode()))
//        assertFalse(date1 === date2) //Not possible for value classes
        assertFalse(date1.hashCode().equals(date3.hashCode()))
//        assertFalse(date1 === date3) //Not possible for value classes
    }

    @Test
    fun `test compare dates`(){
        val date1 = Date(2024, 2,1)
        val date2 = Date(2024, 2,1)
        val date3 = Date(2024, 2,2)

        assertFalse( date1 < date2)
        assertTrue( date1 <= date2)
        assertTrue( date1 <= date3)
        assertTrue( date1 < date3)
        assertFalse( date1 > date3)
        assertTrue( date1 >= date2)
        assertFalse( date1 >= date3)
        assertFalse( date1 > date3)
        assertTrue( date3 > date1)
    }

    @Test
    fun `test date toString`(){
        val date = Date(2025, 2,1)
        println(date)
        println(date.toString())

        assertEquals("2025-02-01", date.toString())
    }
}


