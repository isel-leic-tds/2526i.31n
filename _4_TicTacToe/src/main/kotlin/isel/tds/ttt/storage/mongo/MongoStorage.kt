package isel.tds.ttt.storage.mongo

import com.mongodb.MongoWriteException
import isel.tds.ttt.storage.Serializer
import isel.tds.ttt.storage.Storage

class MongoStorage <Key, Data>(
    collectionName: String,
    driver: MongoDriver,
    val serializer: Serializer<Data>
): Storage<Key, Data> {

    data class Doc(val _id: String, val data: String)
    private fun toDoc(k: Key, data: Data): Doc =
        Doc(k.toString(), serializer.serialize(data))

    val docs = driver.getCollection<Doc>(collectionName)

    override fun create(k: Key, data: Data) {
        try {
            docs.insertDocument(toDoc(k, data))
        }catch(e: MongoWriteException) {
            error("Document $k already exists")
        }
    }

    override fun read(k: Key): Data? =
        docs.getDocument(k.toString())?.let {
            serializer.deserialize(it.data)}


    override fun update(k: Key, data: Data) {
        check( docs.replaceDocument(k.toString(), toDoc(k, data)))
            {"Document $k does not exist to update"}
    }

    override fun delete(k: Key) {
        check( docs.deleteDocument(k.toString()))
        {"Document $k does not exist to delete"}
    }

}