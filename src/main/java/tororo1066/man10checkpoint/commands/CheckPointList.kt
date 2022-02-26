package tororo1066.man10checkpoint.commands

import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.entity.Player
import tororo1066.man10checkpoint.inventory.CheckPointListInventory
import tororo1066.tororopluginapi.sCommand.OnlyPlayerExecutor

class CheckPointList : OnlyPlayerExecutor {
    override fun onCommand(sender: Player, command: Command, label: String, args: Array<out String>): Boolean {
        val inv = CheckPointListInventory(sender)
        inv.open(sender)
        sender.playSound(sender.location, Sound.BLOCK_CHEST_OPEN,1f,2f)
        return true
    }
}