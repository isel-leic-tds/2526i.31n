package isel.tds.ttt.ui.compose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import ttt.composeapp.generated.resources.Res
import ttt.composeapp.generated.resources.ttt_icon

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TicTacToe",
        icon = painterResource(Res.drawable.ttt_icon)
    ) {
        TDSApp()
    }
}