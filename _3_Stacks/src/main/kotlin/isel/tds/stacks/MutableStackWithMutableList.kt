package isel.tds.stacks

class MutableStackWithMutableList<T> {
    private val elems = mutableListOf<T>()
    fun isEmpty(): Boolean = elems.isEmpty()
//    fun pop(): T = elems.removeLast()
    fun pop(): T = top().also{ elems.removeLast() }
    fun top(): T = elems.last()
    fun push(elem: T) {
        elems.add(elem)
    }

    override fun equals(other: Any?): Boolean {
        return other is MutableStackWithMutableList<*> && other.elems == this.elems
    }
    override fun hashCode(): Int {
        return elems.hashCode()
    }
}