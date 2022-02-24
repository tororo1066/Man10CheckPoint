package tororo1066.man10checkpoint.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import tororo1066.man10checkpoint.Man10CheckPoint

class SetAccessCheckPoint : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = Bukkit.getPlayer(args[1])
        if (player == null){
            sender.sendMessage(Man10CheckPoint.prefix + "§4プレイヤーが存在しません")
            return true
        }

        val cPData = Man10CheckPoint.checkPoints[args[2]]
        if (cPData == null){
            sender.sendMessage(Man10CheckPoint.prefix + "§4場所が存在しません")
            return true
        }

        if (args[3].toBoolean()){
            if (cPData.addCheckedPlayer(player.uniqueId)){
                sender.sendMessage(Man10CheckPoint.prefix + "§a追加しました")
                player.sendMessage(Man10CheckPoint.prefix + "§aあなたは${cPData.name}にアクセスできるようになりました！")
            } else {
                sender.sendMessage(Man10CheckPoint.prefix + "§4既に追加済みです")
                player.sendMessage(Man10CheckPoint.prefix + "§4すでにあなたは${cPData.name}にアクセスできるようになっています")
            }
        } else {
            if (cPData.removeCheckedPlayer(player.uniqueId)){
                sender.sendMessage(Man10CheckPoint.prefix + "§a削除しました")
            } else {
                sender.sendMessage(Man10CheckPoint.prefix + "§4既に削除済みです")
            }
        }

        return true
    }
}