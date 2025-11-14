package isel.tds.ttt.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import isel.tds.ttt.model.Player
import org.jetbrains.compose.resources.painterResource
import ttt.composeapp.generated.resources.Res
import ttt.composeapp.generated.resources.circle
import ttt.composeapp.generated.resources.cross

@Composable
fun PlayerView(player: Player?) {
//    Text("I am the lord $player")
    val modifier = Modifier.size(50.dp)
    if (player == null) {
        Box(modifier)
    } else {
        val resource = when (player) {
            Player.O -> Res.drawable.circle
            Player.X -> Res.drawable.cross
        }
        Image(
            painter = painterResource(resource),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Composable
@Preview
fun testPlayerView() {
    Row {
        PlayerView(null)
        PlayerView(Player.X)
        PlayerView(Player.O)
    }
}
