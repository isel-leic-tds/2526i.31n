package isel.tds.ttt.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import isel.tds.ttt.exithandling.ExitHandler
import isel.tds.ttt.model.Game
import isel.tds.ttt.model.Name
import isel.tds.ttt.storage.GameSerializer
import isel.tds.ttt.storage.TextFileStorage
import isel.tds.ttt.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TDSApp() {
    MaterialTheme {
        var scope = rememberCoroutineScope()
        val vm = remember {
            val st = TextFileStorage<Name, Game>("savedGames", GameSerializer)
//                val driver: MongoDriver = MongoDriver("JogoGalo31N")
//                val st = MongoStorage<Name, Game>("savedGames", driver, GameSerializer)
            val myVm = AppViewModel(st, scope)
            ExitHandler.registerExitHandler(myVm::cleanup)
            myVm
        }
        Column {
            MenuBar {
                Menu("Game") {
                    Item("Start clash", onClick = vm::showStartDialog)
                    Item("Join clash", onClick = vm::showJoinDialog)
                    Item("Refresh", enabled = vm.canRefresh, onClick = vm::refresh)
                    Item("New game", enabled = vm.isClashRun, onClick = vm::newGame)
                    Item("Show score", enabled = vm.isClashRun, onClick = vm::toogleShowScore)
                    Item("Exit", onClick = ExitHandler::exit)
                }
            }

            if (vm.isClashRun) {
                BoardView(vm.board, vm::play)//{ pos -> vm.play(pos) })
                StatusBarView(vm.game.gameState, vm.clashRun.sidePlayer, vm.clashRun.name)
            } else {
                Box(Modifier.size(GRID_SIZE, GRID_SIZE + STATUS_HEIGHT))
            }
            if (vm.showScoreDialog) {
                ScoreDialog(vm.game.score, vm::hideScoreDialog)
            }
            vm.startOrJoinDialog?.let {
                StartOrJoinDialog(it, vm::hideStartOrJoinDialog, vm::startOrJoinGame)
            }
            vm.errorMessage?.let { msg -> ErrorDialog(msg, vm::hideErrorDialog) }
        }
        if (vm.isWaiting) WaitingIndicator()
    }
}









