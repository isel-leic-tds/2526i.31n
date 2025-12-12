package isel.tds.ttt.ui.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import isel.tds.ttt.model.Name
import isel.tds.ttt.model.toName

enum class StartOrJoinType(val text: String) {
    StartDialog("Start"),
    JoinDialog("Join")
}

@Composable
fun StartOrJoinDialog(type: StartOrJoinType, close: () -> Unit, startOrJoinGame: (StartOrJoinType, Name) -> Unit) {

    var name by remember { mutableStateOf("") }
    val isValid = Name.isValid(name)
    val fr = remember { FocusRequester() }
    fun keyHandler(ke: KeyEvent): Boolean {
        if (ke.key == Key.Enter && ke.type == KeyEventType.KeyDown) {
            if (isValid)
                startOrJoinGame(type, name.toName())
            return true
        }
        return false
    }
    AlertDialog(
        onDismissRequest = close,
        title = { Text("Name to ${type.text}") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { newValue -> name = newValue },
                label = { Text("Name of game") },
                singleLine = true,
                modifier = Modifier.focusRequester(fr).onKeyEvent(::keyHandler)
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
    LaunchedEffect(Unit) {
        fr.requestFocus()
    }
}