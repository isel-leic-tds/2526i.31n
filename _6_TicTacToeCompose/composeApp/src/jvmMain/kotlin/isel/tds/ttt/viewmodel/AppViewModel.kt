package isel.tds.ttt.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.ttt.model.*
import isel.tds.ttt.ui.compose.StartOrJoinType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppViewModel(val st: GameStorage, val scope: CoroutineScope) {
    var clash by mutableStateOf(Clash(st))
        private set
    var showScoreDialog by mutableStateOf(false)
        private set
    var startOrJoinDialog: StartOrJoinType? by mutableStateOf(null)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var isWaiting: Boolean by mutableStateOf(false)

    val clashRun get() = clash as ClashRun
    val isClashRun get() = clash is ClashRun
    val game: Game get() = clashRun.game
    val board: Board get() = game.board

    val canRefresh: Boolean
        get() = isClashRun &&
                (
                        (game.gameState is Run &&
                                clashRun.sidePlayer != (game.gameState as Run).turn
                                )
                                || game.gameState is Win
                                || game.gameState is Draw
                        )

    //TODO Check Win and Draw cases

    private fun exec(action: Clash.() -> Clash) {
        try {
            clash = clash.action()
        } catch (e: TTTBaseFatalException) {
            cleanup()
            clash = Clash(clash.st)
            errorMessage = e.message
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }

    fun newGame() = exec { new() }
    fun play(pos: Position): Unit {
        exec { play(pos) }
        waitForOtherSide()
    }

    fun waitForOtherSide() {
        isWaiting = true;
        scope.launch {
            do {
                delay(3000)
                try {
                    clash = clash.refresh()
                    isWaiting = false
                } catch (e: NoChangesException) {
                } catch (e: Exception) {
                    errorMessage = e.message
                }
            } while (isWaiting)
        }
    }

    fun refresh() {
//    = exec {
//        val newClash = refresh();
//        isWaiting = false;
//        newClash
        scope.launch {
            try {
                clash = clash.refresh()
                isWaiting = false;
            } catch (e: TTTBaseFatalException) {
                cleanup()
                clash = Clash(clash.st)
                errorMessage = e.message
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun cleanup() = exec { deleteIfIsOwner() }

    fun toogleShowScore() {
        showScoreDialog = !showScoreDialog
    }


    fun hideScoreDialog() {
        showScoreDialog = false
    }

    fun showStartDialog() {
        startOrJoinDialog = StartOrJoinType.StartDialog
    }

    fun showJoinDialog() {
        startOrJoinDialog = StartOrJoinType.JoinDialog
    }

    fun hideStartOrJoinDialog() {
        startOrJoinDialog = null
    }


    fun startOrJoinGame(type: StartOrJoinType, name: Name) {
        if (type == StartOrJoinType.StartDialog)
            clash = clash.start(name)
        else
            clash = clash.join(name)

        hideStartOrJoinDialog()
    }


    fun hideErrorDialog() {
        errorMessage = null
    }

}


