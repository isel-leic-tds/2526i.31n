package isel.tds.ttt.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.tds.ttt.model.BOARD_SIDE_SIZE
import isel.tds.ttt.model.Board
import isel.tds.ttt.model.Position

val CELL_SIZE = 150.dp
val LINE_ThiCKNESS = 5.dp
val GRID_SIZE = CELL_SIZE * BOARD_SIDE_SIZE + LINE_ThiCKNESS * (BOARD_SIDE_SIZE - 1)

@Composable
fun BoardView(board: Board, clickAction: (Position) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Black)
            .height(GRID_SIZE),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(BOARD_SIDE_SIZE) { rowIdx ->
            Row(
                Modifier.width(GRID_SIZE),
                Arrangement.SpaceBetween
            ) {
                repeat(BOARD_SIDE_SIZE) { colIdx ->
                    val position = Position(rowIdx * BOARD_SIDE_SIZE + colIdx)
                    PlayerView(
                        board[position],
                        { clickAction(position) }
                    )
                }
            }
        }
    }
}
