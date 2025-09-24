package isel.tds.isel.tds

//class Date {
//    val year: Int
//    val month: Int
//    val day: Int
//
//    constructor(y: Int, m: Int, d: Int) {
//        year = y
//        month=m
//        day = d
//    }
//
//    constructor(y: Int, m: Int) {
//        year = y
//        month=m
//        day = 1
//    }
//
//    constructor(y: Int) {
//        year = y
//        month=1
//        day = 1
//    }
//}


//class Date(val year: Int, val month: Int, val day: Int){
//
//    constructor(y: Int, m: Int): this(y,m, 1)
//    constructor(y: Int): this(y, 1,1)
//}

//class Date(val year: Int, val month: Int = 1, val day: Int = 1){
//    init{
//        if(year<=0) throw IllegalArgumentException("year is negative or zero")
//        if(month<=0) throw IllegalArgumentException("month is negative or zero")
//        if(day<=0) throw IllegalArgumentException("day is negative or zero")
//    }
//}

//class Date(val year: Int, val month: Int = 1, val day: Int = 1){
//    val isLeapYear: Boolean = year%4 == 0 && year%100!=0 || year%400 == 0
//
//    init{
//        require( year>0){"year is negative or zero"}
//        require(month>0 && month <=12 ){"month is negative or zero"}
//        require(day>0){"day is negative or zero"}
//    }
//}

class Date(val year: Int, val month: Int = 1, val day: Int = 1){


    init{
        require( year>0){"year is negative or zero"}
        require(month>0 && month <=12 ){"month is negative or zero"}
        require(day>0 && day <= lastDayOfMonth){"day is negative or zero"}
    }
}

val Date.isLeapYear: Boolean
    get() = year%4 == 0 && year%100!=0 || year%400 == 0

private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val Date.isLastDayOfMonth: Boolean
    get() = day == this.lastDayOfMonth

private val Date.lastDayOfMonth: Int
    get() = if( month==2 && this.isLeapYear) 29 else daysOfMonth[this.month-1]

fun Date.addDays(daystoAdd: Int) = Date(1,1,1)

operator fun Date.plus(daystoAdd: Int) = Date(1,1,1)










