package isel.tds.ttt.ui.console

data class LineCommand(val cmdStr: String, val args: List<String>)

fun readCommand(): LineCommand{
    val input = readln().uppercase().split(' ')
    return LineCommand(input[0], input.drop(1))
//    val cmdStr = input[0]
//    val args: List<String> = input.drop(1)

}
