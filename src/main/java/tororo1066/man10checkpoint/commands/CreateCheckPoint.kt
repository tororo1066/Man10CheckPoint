package tororo1066.man10checkpoint.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import tororo1066.man10checkpoint.CheckPointData
import tororo1066.man10checkpoint.Man10CheckPoint
import tororo1066.tororopluginapi.sCommand.OnlyPlayerExecutor
import java.io.File

class CreateCheckPoint : OnlyPlayerExecutor {
    override fun onCommand(sender: Player, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.inventory.itemInMainHand.type.isAir){
            sender.sendMessage(Man10CheckPoint.prefix + "§4手にアイテムを持ってください")
            return true
        }
        val file = File(Man10CheckPoint.plugin.dataFolder.path + "/CheckPoints/${args[1]}.yml")
        if (file.exists()){
            sender.sendMessage(Man10CheckPoint.prefix + "§4すでにその内部名は存在します")
            return true
        }
        file.createNewFile()
        val yml = YamlConfiguration.loadConfiguration(file)
        yml.set("name",args[2])
        yml.set("location",sender.location)
        yml.set("item",sender.inventory.itemInMainHand)
        yml.set("checkedPlayer",null)
        yml.save(file)

        val data = CheckPointData.loadFromConfig(args[1])

        Man10CheckPoint.checkPoints[file.nameWithoutExtension] = data
        sender.sendMessage(Man10CheckPoint.prefix + "§a作成しました")
        return true
    }
}