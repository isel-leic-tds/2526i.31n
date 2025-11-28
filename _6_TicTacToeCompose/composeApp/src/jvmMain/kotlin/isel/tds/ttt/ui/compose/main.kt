package isel.tds.ttt.ui.compose

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import isel.tds.ttt.exithandling.ExitHandler
import org.jetbrains.compose.resources.painterResource
import ttt.composeapp.generated.resources.Res
import ttt.composeapp.generated.resources.cross

fun main() = application {
    ExitHandler.registerExitApplication(::exitApplication)
    Window(
        state = WindowState(size = DpSize.Unspecified),
        onCloseRequest = ExitHandler::exit,
        title = "TicTacToe",
        icon = painterResource(Res.drawable.cross),
        resizable = false
    ) {
        TDSApp()
    }
}