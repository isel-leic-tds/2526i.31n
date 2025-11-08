package isel.tds.democompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.FrameWindowScope
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FrameWindowScope.TDSApp() {
    println("inside TDSApp")
    MaterialTheme {
        println("inside MaterialTheme")
        var text by remember { println("inside remember init1"); mutableStateOf("Hello World") }

        var counter = 0
        Column() {
            println("inside Column")
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow),
                onClick = { text = "BUUUUUUUU1" + (++counter); window.title = text }) {
                println("inside Button1")
                Text(text = text)
            }
            var text2 by remember { println("inside remember init2"); mutableStateOf("Hello World") }

            Button(onClick = { text2 = "BUUUUUUUU2" + (++counter); window.title = text2 }) {
                println("inside Button2")
                Text(text = text2)
            }
        }
    }
}