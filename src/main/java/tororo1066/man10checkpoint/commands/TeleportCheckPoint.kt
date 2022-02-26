package tororo1066.man10checkpoint.commands

import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.entity.Player
import tororo1066.man10checkpoint.Man10CheckPoint
import tororo1066.tororopluginapi.sCommand.OnlyPlayerExecutor

class TeleportCheckPoint : OnlyPlayerExecutor {
    override fun onCommand(sender: Player, command: Command, label: String, args: Array<out String>): Boolean {
        val cPData = Man10CheckPoint.checkPoints[args[1]]
        if (cPData == null){
            sender.sendMessage(Man10CheckPoint.prefix + "§4場所が存在しません")
            return true
        }

        if (!cPData.checkedPlayers.contains(sender.uniqueId)){
            if (sender.isOp){
                sender.sendMessage(Man10CheckPoint.prefix + "§bあなたはOPのためアクセス権限を無視しました")
            } else {
                sender.sendMessage(Man10CheckPoint.prefix + "§4あなたはそこのチェックポイントにアクセスできません")
                return true
            }
        }

        sender.teleport(cPData.location)
        sender.playSound(sender.location, Sound.ENTITY_ENDERMAN_TELEPORT,1f,1f)
        sender.sendMessage(Man10CheckPoint.prefix + "§d${cPData.name}§aに転送しました")
        return true
    }
}