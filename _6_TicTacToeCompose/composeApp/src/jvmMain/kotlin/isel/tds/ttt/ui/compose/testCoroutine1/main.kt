package isel.tds.ttt.ui.compose.testCoroutine1

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*

fun log(lab: String) = println("$lab: ${Thread.currentThread().name}")


fun main1() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            Button(onClick = { log("onClick") }) { Text("Ok") }
        }
    }
    log("exit")
}

//O click no botão simula trabalho demorado e pendura a UI thread
fun main2() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("window")
            var clickable by remember { mutableStateOf(true) }
            Row {
                Button(
                    enabled = clickable,
                    onClick = {
                        log("onClick1");
                        clickable = false
                        repeat(5) { print('.'); Thread.sleep(1000) }
                    })
                { Text("Click me") }
                Button(
                    enabled = !clickable,
                    onClick = { log("onClick2"); clickable = true })
                { Text("Enable click") }
            }
        }
    }
    log("exit")
}

fun main() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            log("window")
            var scope = rememberCoroutineScope()
            var clickable by remember { mutableStateOf(true) }
            var job: Job? by remember { mutableStateOf(null) }
            Row {
                Button(
                    enabled = clickable,
                    onClick = {
                        log("onClick1");
                        clickable = false
                        job = scope.launch {
                            log("onClick1 inside coroutine");
                            var heavyExecutionReturn = withContext(Dispatchers.IO) {
                                log("onClick1 inside coroutine in IO thread");
                                repeat(5) { print('.'); delay(1000) }
                                "HeavyReturnObject"
                            }
                            log(heavyExecutionReturn)
                            job = null
                        }
                    })
                { Text("Click me") }
                Button(
                    enabled = !clickable,
                    onClick = { log("onClick2"); clickable = true; job?.cancel(); })
                { Text("Enable click") }
            }
        }
    }
    log("exit")
}

