package tororo1066.man10checkpoint.commands

import org.bukkit.command.Command
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import tororo1066.man10checkpoint.CheckPointData
import tororo1066.man10checkpoint.Man10CheckPoint
import tororo1066.tororopluginapi.sCommand.OnlyPlayerExecutor
import java.io.File

class DeleteCheckPoint : OnlyPlayerExecutor {
    override fun onCommand(sender: Player, command: Command, label: String, args: Array<out String>): Boolean {
        val file = File(Man10CheckPoint.plugin.dataFolder.path + "/CheckPoints/${args[1]}.yml")
        if (!file.exists()){
            sender.sendMessage(Man10CheckPoint.prefix + "§4その内部名は存在しません")
            return true
        }
        file.delete()

        Man10CheckPoint.checkPoints.remove(args[1])
        sender.sendMessage(Man10CheckPoint.prefix + "§a削除しました")
        return true
    }
}