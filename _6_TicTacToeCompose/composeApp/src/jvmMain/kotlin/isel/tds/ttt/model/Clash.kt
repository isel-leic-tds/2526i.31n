package isel.tds.ttt.model

import isel.tds.ttt.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias GameStorage = Storage<Name, Game>

open class Clash(val st: GameStorage)

class ClashRun(
    st: GameStorage,
    val name: Name,
    val sidePlayer: Player,
    val game: Game
) : Clash(st)

fun Clash.play(pos: Position): Clash = when (this) {
    is ClashRun -> {
        check((game.gameState as Run).turn == sidePlayer) { "Not your turn" }
        val newGame = game.play(pos)
        st.update(name, newGame)
        ClashRun(st, name, sidePlayer, newGame)
    }

    else -> error("Game not started")
}

fun Clash.start(name: Name): ClashRun {
    return ClashRun(this.st, name, Player.X, Game()).also {
        st.create(name, it.game)
    }
}

fun Clash.join(name: Name): ClashRun {
    val readGame = st.read(name)
    checkNotNull(readGame) { "Game with name=$name does not exist" }
    return ClashRun(this.st, name, Player.O, readGame)
}

suspend fun Clash.refresh(): ClashRun {
    check(this is ClashRun) { "Game not started" }
    val readGame = withContext(Dispatchers.IO) {
        Thread.sleep(2000)
        st.read(name)
    }
    if (readGame == null) {
        throw TTTNoStorageException()//"Game with name=$name does not exist")
    }
    if (this.game == readGame)
        throw NoChangesException()
    return ClashRun(this.st, name, sidePlayer, readGame)
}

fun Clash.new(): ClashRun {
    check(this is ClashRun) { "Game not started" }
    val newClash = when (this.game.gameState) {
        is Run -> {
            if (sidePlayer == game.gameState.turn) {
                ClashRun(st, name, sidePlayer, game.restartGame())
            } else {
                this
            }
        }

        is Win, is Draw -> ClashRun(st, name, sidePlayer, game.restartGame())
    }
    st.update(name, newClash.game)
    return newClash
}

fun Clash.deleteIfIsOwner(): Clash {
    if (this is ClashRun && sidePlayer == Player.X) {
        st.delete(name)
    }
    return this
}