package isel.tds.exam_slotmachine_2324_t2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import isel.tds.exam_slotmachine_2324_t2.view.SlotMachineApp
import isel.tds.exam_slotmachine_2324_t2.viewmodel.SlotMachineViewModel

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "exam_slotmachine_2324_t2",
//    ) {
//        App()
//    }
//}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Slot Machine")
    { MaterialTheme { SlotMachineApp() } }
}