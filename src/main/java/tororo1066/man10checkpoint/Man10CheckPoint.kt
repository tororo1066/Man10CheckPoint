package tororo1066.man10checkpoint

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Man10CheckPoint : JavaPlugin() {

    companion object{
        lateinit var plugin: Man10CheckPoint
        val checkPoints = HashMap<String,CheckPointData>()
        const val prefix = "§e§l[§d§lMan10§a§lCheckPoint§e§l]"
        val useWorlds = ArrayList<String>()
        fun reload(){
            plugin.saveDefaultConfig()
            checkPoints.clear()
            val folder = File(plugin.dataFolder.path + "/CheckPoints")
            if (!folder.exists()) folder.mkdir()
            if (folder.listFiles() != null){
                for (file in folder.listFiles()!!){
                    val data = CheckPointData.loadFromConfig(file.nameWithoutExtension)
                    if (!data.loaded) continue
                    checkPoints[file.nameWithoutExtension] = data
                }
            }
            CheckPointCommand()
            useWorlds.addAll(plugin.config.getStringList("useWorlds"))
        }
    }

    override fun onEnable() {
        saveDefaultConfig()
        plugin = this
        reload()
        CheckPointListener()
    }


}