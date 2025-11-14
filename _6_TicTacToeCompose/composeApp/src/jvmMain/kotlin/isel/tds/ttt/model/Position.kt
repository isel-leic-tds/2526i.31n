package isel.tds.ttt.model

data class Position private constructor(val index: Int){

    val row: Int get() = index / BOARD_SIDE_SIZE  // row in (0..<BOARD_SIZE)
    val col: Int get() = index % BOARD_SIDE_SIZE  // col in (0..<BOARD_SIZE)
    val backSlash get() = row == col         // Is in principal diagonal
    val slash get() = row+col == BOARD_SIDE_SIZE-1// Is in secondary diagonal

    companion object {
        val positionValues: List<Position>
            = List(BOARD_TOTAL_SIZE){idx->Position(idx)}

        operator fun invoke(idx: Int): Position{
            require( idx >=0 && idx < BOARD_TOTAL_SIZE){"Invalid index postion"}
            return positionValues[idx]
        }
    }
}

fun Int.toPositionOrNull() = Position.positionValues.getOrNull(this)
fun Int.toPosition(): Position = Position(this)
fun String.toPosition(): Position = Position(this.toInt())
