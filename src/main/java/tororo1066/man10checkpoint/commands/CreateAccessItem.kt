package tororo1066.man10checkpoint.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import tororo1066.man10checkpoint.Man10CheckPoint
import tororo1066.tororopluginapi.sCommand.OnlyPlayerExecutor
import tororo1066.tororopluginapi.sItem.SItem

class CreateAccessItem : OnlyPlayerExecutor {
    override fun onCommand(sender: Player, command: Command, label: String, args: Array<out String>): Boolean {
        val cPData = Man10CheckPoint.checkPoints[args[1]]
        if (cPData == null){
            sender.sendMessage(Man10CheckPoint.prefix + "§4場所が存在しません")
            return true
        }

        val item = SItem(Material.valueOf(args[2])).setDisplayName(args[3].replace("&","§")).setLore(ArrayList<String>(args.asList()).subList(4,args.size).map { it.replace("&","§") }).setCustomData(Man10CheckPoint.plugin,"AccessPoint", PersistentDataType.STRING, args[1])
        sender.inventory.addItem(item)
        sender.sendMessage(Man10CheckPoint.prefix + "§aアイテムを付与しました")
        return true
    }
}