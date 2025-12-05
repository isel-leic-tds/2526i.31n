package isel.tds.ttt.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.tds.ttt.model.*

val STATUS_HEIGHT = 50.dp

@Composable
fun LabelledCell(text: String, player: Player?) {
    Text(text)
//    player?.let{PlayerView(player, modifier = Modifier.size(50.dp))}
    if (player != null)
        PlayerView(player, modifier = Modifier.size(50.dp))
}

@Composable
fun StatusBarView(gameState: GameState, you: Player, name: Name) =
    Row(
        Modifier.background(Color.LightGray)
            .width(GRID_SIZE)
            .height(STATUS_HEIGHT),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LabelledCell("You:", you)
        Spacer(Modifier.width(30.dp))
        val (text, player) = when (gameState) {
            is Run -> "Turn:" to gameState.turn
            is Draw -> "Draw" to null
            is Win -> "Winner:" to gameState.winner
        }
        LabelledCell(text, player)
        Spacer(Modifier.width(30.dp))
        Text("on game ${name.toString()}")
    }
