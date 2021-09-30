package me.zowpy.configuration

import com.google.gson.*
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class JsonConfigurationFile(dir: File, n: String) : ConfigurationFile {

    private var file: File
    private var gson: Gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()
    var element: JsonElement
    var prettyPainting: Boolean = false

    init {
        val name = "$n.json"
        if (!dir.exists())
            dir.mkdirs()
        file = File(dir, name)

        if (!file.exists())
            file.createNewFile()

        element = JsonParser.parseReader(FileReader(file))

        if (element.isJsonNull) {
            element = JsonObject()
        }
    }

    override fun save() {
        val fileWriter = FileWriter(file)
        if (!prettyPainting) {
            fileWriter.write(element.toString())
        }else {
            fileWriter.write(gson.toJson(element))
        }

        fileWriter.flush()
        fileWriter.close()
    }

    override fun reload() {
        element = JsonParser.parseReader(FileReader(file))

        if (element.isJsonNull) {
            element = JsonObject()
        }
    }
}