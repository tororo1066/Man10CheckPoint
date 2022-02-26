package tororo1066.man10checkpoint

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import tororo1066.tororopluginapi.otherUtils.UsefulUtility
import java.io.File
import java.util.UUID

class CheckPointData {

    var id = ""
    var name = ""
    lateinit var location: Location
    val checkedPlayers = ArrayList<UUID>()

    var loaded = false

    lateinit var configFile: File
    lateinit var config: YamlConfiguration

    companion object{
        fun loadFromConfig(id: String): CheckPointData {
            val file = File(Man10CheckPoint.plugin.dataFolder.path + "/CheckPoints/${id}.yml")
            if (!file.exists()) return CheckPointData()
            if (file.extension != "yml") return CheckPointData()
            val yml = YamlConfiguration.loadConfiguration(file)
            val data = CheckPointData()
            data.id = id
            data.name = yml.getString("name")?:"Null"
            data.location = yml.getLocation("location")?: return CheckPointData()
            yml.getStringList("checkedPlayers").forEach {
                data.checkedPlayers.add(UUID.fromString(it))
            }
            data.config = yml
            data.configFile = file
            data.loaded = true
            return data
        }
    }


    fun addCheckedPlayer(uuid: UUID): Boolean {
        if (checkedPlayers.contains(uuid))return false
        checkedPlayers.add(uuid)
        val list = Man10CheckPoint.plugin.config.getStringList("checkedPlayers")
        list.add(uuid.toString())
        config.set("checkedPlayers",list)
        config.save(configFile)
        return true
    }

    fun removeCheckedPlayer(uuid: UUID): Boolean {
        if (!checkedPlayers.contains(uuid))return false
        checkedPlayers.remove(uuid)
        val list = Man10CheckPoint.plugin.config.getStringList("checkedPlayers")
        list.remove(uuid.toString())
        config.set("checkedPlayers",list)
        config.save(configFile)
        return true
    }

    fun checkCheckedPlayer(uuid: UUID): Boolean {
        return checkedPlayers.contains(uuid)
    }
}