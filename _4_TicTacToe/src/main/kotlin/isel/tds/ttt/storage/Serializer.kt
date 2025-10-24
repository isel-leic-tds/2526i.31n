package isel.tds.ttt.storage

import javax.xml.crypto.Data

interface Serializer<Data> {
    fun serialize(data: Data): String
    fun deserialize(data: String): Data
}