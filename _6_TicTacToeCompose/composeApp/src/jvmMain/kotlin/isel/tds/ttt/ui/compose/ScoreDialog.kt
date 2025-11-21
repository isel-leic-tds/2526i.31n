package isel.tds.ttt.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import isel.tds.ttt.model.Player
import isel.tds.ttt.model.Score

@Composable
fun ScoreDialog(score: Score, closeDialodAction: () -> Unit) = AlertDialog(
    onDismissRequest = closeDialodAction,
    title = { Text("Score") },
    text = { ScoreDialogContent(score) },
    confirmButton = { TextButton(onClick = closeDialodAction) { Text("Close") } }
)

@Composable
fun ScoreDialogContent(score: Score) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
        Column {
            Player.entries.forEach { player ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PlayerView(
                        player = player,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(" - ${score[player]}")
                }
            }
        }
        Text(text = "Draws - ${score[null]}")
    }
}
//    Text("Show Score")
//}