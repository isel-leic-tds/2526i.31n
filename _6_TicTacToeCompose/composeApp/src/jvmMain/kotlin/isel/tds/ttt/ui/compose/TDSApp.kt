package isel.tds.ttt.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import isel.tds.ttt.model.Player
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun TDSApp() {
    MaterialTheme {
        Column {
            var player by remember { mutableStateOf(Player.X) }

            Button({ player = player.other() }) {
                Text("other player")
            }
            PlayerView(player)
        }
    }
}








