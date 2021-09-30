package me.zowpy.configuration

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class YamlConfigurationFile(plugin: Plugin, n: String) : ConfigurationFile {

    private var file: File
    var config: YamlConfiguration


    init {
        val name = "$n.yml"

        if (!plugin.dataFolder.exists())
            plugin.dataFolder.mkdirs()

        file = File(plugin.dataFolder, name)

        if (!file.exists())
            file.createNewFile()

        config = YamlConfiguration.loadConfiguration(file)
    }

    override fun save() {
        config.save(file)

    }

    override fun reload() {
        config = YamlConfiguration.loadConfiguration(file)
    }


}