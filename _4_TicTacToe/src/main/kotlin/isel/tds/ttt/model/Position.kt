package isel.tds.ttt.model

data class Position private constructor(val index: Int){

    companion object {
        private val positionValues: List<Position>
            = List(BOARD_TOTAL_SIZE){idx->Position(idx)}

        operator fun invoke(idx: Int): Position = positionValues[idx]
    }
}

fun Int.toPosition(): Position = Position(this)
fun String.toPosition(): Position = Position(this.toInt())
