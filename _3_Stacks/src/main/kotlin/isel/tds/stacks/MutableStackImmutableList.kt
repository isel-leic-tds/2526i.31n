package isel.tds.stacks

class MutableStackImmutableList<T> {
    private var elems = emptyList<T>()
    fun isEmpty(): Boolean = elems.isEmpty()
//    fun pop(): T {
//        val e = elems.last()
//        elems = elems.dropLast(1)
//        return e
//    }
fun pop(): T = (elems.last()).also {  elems = elems.dropLast(1) }

    fun top(): T = elems.last()
    fun push(elem: T) {
        elems = elems + elem
    }

    override fun equals(other: Any?): Boolean {
        return other is MutableStackImmutableList<*> && other.elems == this.elems
    }
    override fun hashCode(): Int {
        return elems.hashCode()
    }
}