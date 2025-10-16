package isel.tds.ttt.model

data class Position private constructor(val index: Int){

    companion object {
        private val positionValues: List<Position>
            = List(BOARD_TOTAL_SIZE){idx->Position(idx)}

        operator fun invoke(idx: Int): Position{
            require( idx >=0 && idx < BOARD_TOTAL_SIZE){"Invalid index postion"}
            return positionValues[idx]
        }
    }
}

fun Int.toPosition(): Position = Position(this)
fun String.toPosition(): Position = Position(this.toInt())
