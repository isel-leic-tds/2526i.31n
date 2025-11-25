package isel.tds.ttt.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Name
import isel.tds.ttt.storage.GameSerializer
import isel.tds.ttt.storage.TextFileStorage
import isel.tds.ttt.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TDSApp(onExit: () -> Unit) {
    MaterialTheme {
        Column {
            val vm = remember { AppViewModel(TextFileStorage<Name, Game>("savedGames", GameSerializer)) }
            MenuBar {
                Menu("Game") {
                    Item("Start clash", onClick = vm::showStartDialog)
                    Item("Join clash", onClick = vm::showJoinDialog)
                    Item("Refresh", onClick = vm::refresh)
                    Item("New game", onClick = vm::newGame)
                    Item("Show score", onClick = vm::toogleShowScore)
                    Item("Exit", onClick = onExit)
                }
            }

            if (vm.isRun) {
                BoardView(vm.board, vm::play)//{ pos -> vm.play(pos) })
                StatusBarView(vm.game.gameState)
            } else {
                Box(Modifier.size(GRID_SIZE, GRID_SIZE + STATUS_HEIGHT))
            }
            if (vm.showScoreDialog) {
                ScoreDialog(vm.game.score, vm::hideScoreDialog)
            }
            vm.startOrJoinDialog?.let {
                StartOrJoinDialog(it, vm::hideStartOrJoinDialog, vm::startOrJoinGame)
            }
        }
    }
}









