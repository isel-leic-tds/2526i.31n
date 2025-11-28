package isel.tds.ttt.exithandling

object ExitHandler {

    var exitHandlers: List<() -> Unit> = emptyList()
        private set

    var exitApplication: (() -> Unit)? = null
        private set

    fun exit() {
        //call exit handlers
        exitHandlers.forEach { exitHandler -> exitHandler() }
        //call exit application function
        val exitApp = checkNotNull(exitApplication) { "ExitHandler is not configured" }
        exitApp()
    }

    fun registerExitHandler(exitHandler: () -> Unit) {
        exitHandlers = exitHandlers + exitHandler
    }

    fun registerExitApplication(exitApp: () -> Unit) {
        exitApplication = exitApp
    }
}