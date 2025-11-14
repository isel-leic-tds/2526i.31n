package isel.tds.ttt.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.play
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TDSApp(onExit: () -> Unit) {
    MaterialTheme {
        Column {
            MenuBar {
                Menu("Game") {
                    Item("Exit", onClick = onExit)
                }
            }
            var game by remember { mutableStateOf(Game()) }

            BoardView(game.board, { pos -> game = game.play(pos) })
            StatusBarView(game.gameState)

//            var player by remember { mutableStateOf(Player.X) }
//
//            Button({ player = player.other() }) {
//                Text("other player")
//            }
//            PlayerView(player)
        }
    }
}










