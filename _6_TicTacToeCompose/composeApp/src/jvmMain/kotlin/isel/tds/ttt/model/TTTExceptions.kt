package isel.tds.ttt.model

open class TTTBaseFatalException(msg: String) : Exception(msg)

class TTTNoStorageException() : TTTBaseFatalException("No Storage Exception")

