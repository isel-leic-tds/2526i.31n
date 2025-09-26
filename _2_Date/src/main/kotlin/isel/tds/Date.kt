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

//class Date(val year: Int, val month: Int = 1, val day: Int = 1){
//    init{
//        require( year>0){"year is negative or zero"}
//        require(month>0 && month <=12 ){"month is negative or zero"}
//        require(day>0 && day <= lastDayOfMonth){"day is negative or zero"}
//    }
//
//     operator override fun equals(other: Any?): Boolean =
//        other is Date &&
//                year == other.year && month == other.month && day == other.day
//
//    override fun hashCode(): Int =
//        (year * 12 + month * 31) + day
//
//    override fun toString(): String
//        = "$year-" + "%02d-%02d".format(month, day)
//}
private const val DAY_BITS = 5 // 0..31
private const val MONTH_BITS = 4 // 0..15
private const val YEAR_BITS = 12 // 0..4k

//class Date private constructor(private val bits: Int){
//
//    constructor(year: Int, month: Int = 1, day: Int = 1):
//            this(year shl (MONTH_BITS + DAY_BITS) or (month shl DAY_BITS) or day)
//    {
//        require( year>0){"year is negative or zero"}
//        require(month>0 && month <=12 ){"month is negative or zero"}
//        require(day>0 && day <= lastDayOfMonth){"day is negative or zero"}
//    }
//
//    val year: Int get() = bits shr (MONTH_BITS + DAY_BITS)
//    val month: Int get() = bits shr DAY_BITS and ((1 shl MONTH_BITS) - 1)
//    val day: Int get() = bits and ((1 shl DAY_BITS) - 1)
//
//    operator override fun equals(other: Any?): Boolean =
//        other is Date &&
//                year == other.year && month == other.month && day == other.day
//
//    override fun hashCode(): Int =
//        (year * 12 + month * 31) + day
//
//    override fun toString(): String
//            = "$year-" + "%02d-%02d".format(month, day)
//}
@JvmInline
value class Date private constructor(private val bits: Int){

    constructor(year: Int, month: Int = 1, day: Int = 1):
            this(year shl (MONTH_BITS + DAY_BITS) or (month shl DAY_BITS) or day)
    {
        require( year>0){"year is negative or zero"}
        require(month>0 && month <=12 ){"month is negative or zero"}
        require(day>0 && day <= lastDayOfMonth){"day is negative or zero"}
    }

    val year: Int get() = bits shr (MONTH_BITS + DAY_BITS)
    val month: Int get() = bits shr DAY_BITS and ((1 shl MONTH_BITS) - 1)
    val day: Int get() = bits and ((1 shl DAY_BITS) - 1)

//    operator override fun equals(other: Any?): Boolean =
//        other is Date &&
//                year == other.year && month == other.month && day == other.day

//    override fun hashCode(): Int =
//        (year * 12 + month * 31) + day

    override fun toString(): String
            = "$year-" + "%02d-%02d".format(month, day)
}

val Date.isLeapYear: Boolean
    get() = year%4 == 0 && year%100!=0 || year%400 == 0

private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val Date.isLastDayOfMonth: Boolean
    get() = day == this.lastDayOfMonth

private val Date.lastDayOfMonth: Int
    get() = if( month==2 && this.isLeapYear) 29 else daysOfMonth[this.month-1]

fun Date.addDays(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth -> Date(year, month, day + daysToAdd)
    month < 12 -> Date( year, month +1, 1).addDays(calculateRemainderDays(daysToAdd))
    else -> Date( year+1, 1, 1).addDays(calculateRemainderDays(daysToAdd))
}
tailrec fun Date.addDaysNotRecursive(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth -> Date(year, month, day + daysToAdd)
    month < 12 -> Date( year, month +1, 1).addDaysNotRecursive(calculateRemainderDays(daysToAdd))
    else -> Date( year+1, 1, 1).addDaysNotRecursive(calculateRemainderDays(daysToAdd))
}

private fun Date.calculateRemainderDays(daysToAdd: Int): Int
    = daysToAdd - (lastDayOfMonth - day + 1)


operator fun Date.plus(daysToAdd: Int) = this.addDays(daysToAdd)
operator fun Int.plus(date: Date) = date.addDays(this)

operator fun Date.compareTo(otherDate: Date): Int = when{
    year != otherDate.year -> year - otherDate.year
    month!=otherDate.month -> month - otherDate.month
    else -> day - otherDate.day
}








