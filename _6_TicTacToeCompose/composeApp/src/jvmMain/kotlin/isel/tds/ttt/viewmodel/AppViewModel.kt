package isel.tds.ttt.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.ttt.model.*
import isel.tds.ttt.ui.compose.StartOrJoinType

class AppViewModel(val st: GameStorage) {
    var clash by mutableStateOf(Clash(st))
        private set

    //    var game by mutableStateOf(Game())
//    private set
    var showScoreDialog by mutableStateOf(false)
        private set
    var startOrJoinDialog: StartOrJoinType? by mutableStateOf(null)

    val run get() = clash as ClashRun
    val isRun get() = clash is ClashRun
    val game: Game get() = run.game
    val board: Board get() = game.board
    fun newGame() {
        clash = clash.new()
    }

    fun toogleShowScore() {
        showScoreDialog = !showScoreDialog
    }

    fun play(pos: Position): Unit {
        clash = clash.play(pos)
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

    fun refresh() {
        clash = clash.refresh()
    }

    fun startOrJoinGame(type: StartOrJoinType, name: Name) {
        if (type == StartOrJoinType.StartDialog)
            clash = clash.start(name)
        else
            clash = clash.join(name)

        hideStartOrJoinDialog()
    }

}