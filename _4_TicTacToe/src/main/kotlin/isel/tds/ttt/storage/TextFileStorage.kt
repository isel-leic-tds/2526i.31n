package isel.tds.ttt.storage

import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

class TextFileStorage<Key, Data>(
    baseDirectory: String,
    val serializer: Serializer<Data>
): Storage<Key, Data> {

    val fs = FileSystem.SYSTEM
    val basePath: Path = baseDirectory.toPath()

    init {
        with(basePath) {
            if (!exists()) createDirectory()
            else check(isDirectory())
        }
    }

    private fun Path.isDirectory() = fs.metadata(this).isDirectory
    private fun Path.createDirectory() = fs.createDirectory(this)
    private fun Path.exists() = fs.exists(this)
    private fun Path.writeText(data: Data): Unit{
        fs.write(this){this.writeUtf8(serializer.serialize(data)) }
    }
    private fun Path.delete(): Unit = fs.delete(this)

    private fun Path.readText(): Data? =
        fs.read(this){serializer.deserialize(this.readUtf8()) }

    private fun <R>withPath(key: Key, fx: Path.()->R): R =
        (basePath / "$key.txt").fx()

    private fun <R>withPath2(key: Key, fx: (Path)->R): R =
        fx((basePath / "$key.txt"))

//    override fun create(key: Key, data: Data) = withPath(key){
//        check( !exists()){"File already exists"}
//        writeText(data)
//    }
override fun create(key: Key, data: Data) = withPath2(key){
        filePath ->
    check( !filePath.exists()){"File already exists"}
    filePath.writeText(data)
}

    override fun read(key: Key): Data? = withPath(key){
        if(!exists()) null
        else readText()
    }

    override fun update(key: Key, data: Data) = withPath(key){
        check( exists()){"File $key does not exist"}
        writeText(data)
    }

    override fun delete(key: Key) = withPath(key){
        check( exists()){"File $key does not exist"}
        delete()
    }
}