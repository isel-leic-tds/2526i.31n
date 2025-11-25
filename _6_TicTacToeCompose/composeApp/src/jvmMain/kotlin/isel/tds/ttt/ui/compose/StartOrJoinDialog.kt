package isel.tds.ttt.ui.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import isel.tds.ttt.model.Name
import isel.tds.ttt.model.toName

enum class StartOrJoinType(val text: String) {
    StartDialog("Start"),
    JoinDialog("Join")
}

@Composable
fun StartOrJoinDialog(type: StartOrJoinType, close: () -> Unit, startOrJoinGame: (StartOrJoinType, Name) -> Unit) {

    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = close,
        title = { Text("Name to ${type.text}") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { newValue -> name = newValue },
                label = { Text("Name of game") }
            )
        },
        confirmButton = {
            TextButton(
                enabled = Name.isValid(name),
                onClick = { startOrJoinGame(type, name.toName()) }
            ) { Text(type.text) }
        },
        dismissButton = { TextButton(onClick = close) { Text("Cancel") } }
    )
}