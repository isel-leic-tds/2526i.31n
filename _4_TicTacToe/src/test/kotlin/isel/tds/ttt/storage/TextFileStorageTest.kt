package isel.tds.ttt.storage

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class TextFileStorageTest {
    @Test
    fun `CRUD operations tests`() {
        val testSerializer = object: Serializer<Double>{
            override fun serialize(data: Double): String = data.toString()
            override fun deserialize(data: String): Double = data.toDouble()
        }
        val st = TextFileStorage<String, Double>("testSavedGames", testSerializer)

        val key ="fileSave1"
        st.create(key, 3.14)

        val readValue = st.read(key)

        assertEquals(3.14, readValue)

        st.delete(key)
        assertNull(st.read(key))
        assertFailsWith<IllegalStateException> { st.delete(key) }
        assertFailsWith<IllegalStateException> { st.update(key,0.0) }
    }
}