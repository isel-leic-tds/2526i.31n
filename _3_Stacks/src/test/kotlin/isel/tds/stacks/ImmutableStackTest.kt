package isel.tds.stacks

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ImmutableStackTest {
    @Test
    fun `test empty stack`() {
        val stack = ImmutableStack<Char>()
        assertTrue(stack.isEmpty())
        assertFailsWith<NoSuchElementException> { stack.pop() }
        assertFailsWith<NoSuchElementException> { stack.top() }
    }

    @Test
    fun `test not empty stack`() {
        val stackEmpty = ImmutableStack<Char>()
        assertTrue(stackEmpty.isEmpty())
        val stackWithA = stackEmpty.push('A')
        assertFalse(stackWithA.isEmpty())
        assertEquals('A', stackWithA.top())
        val stackWithAB = stackWithA.push('B')
        assertFalse(stackWithAB.isEmpty())
        assertEquals('B', stackWithAB.top())
        val pairWithABPopB = stackWithAB.pop()
        assertEquals('B', pairWithABPopB.first)
        assertFalse(pairWithABPopB.second.isEmpty())
        val pairWithABPopBA = pairWithABPopB.second.pop()
        assertEquals('A', pairWithABPopBA.first)

        assertTrue(pairWithABPopBA.second.isEmpty())
        assertFailsWith<NoSuchElementException> { pairWithABPopBA.second.pop() }
        assertFailsWith<NoSuchElementException> { pairWithABPopBA.second.top() }
    }

    @Test
    fun testEqualityOfStacks(){
        val stackEmpty = ImmutableStack<Char>()
        val stackWithA = stackEmpty.push('A')
        val stackWithAB = stackWithA.push('B')
        val stackEmpty2 = ImmutableStack<Char>()
        val stackWithA2 = stackEmpty2.push('A')
        val stackWithAB2 = stackWithA2.push('B')
        assertEquals(stackWithAB, stackWithAB2)
        assertEquals(stackWithAB.hashCode(), stackWithAB2.hashCode())
        val stackWithABPopB2 = stackWithAB2.pop().second
        assertNotEquals(stackWithAB2, stackWithABPopB2)
        assertNotEquals(stackWithAB2.hashCode(), stackWithABPopB2.hashCode())
    }

    @Test
    fun `test iteration over my stack`(){
//        val myList = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G')
//        for (e in myList) {
//            println(e)
//        }
        val myStack = ImmutableStack<Char>()
        val myStackWithAB = myStack.push('A').push('B')
        for (e in myStackWithAB) {
            println(e)
        }
    }
}