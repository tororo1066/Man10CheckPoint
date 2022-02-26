package tororo1066.man10checkpoint.inventory

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import tororo1066.man10checkpoint.Man10CheckPoint
import tororo1066.tororopluginapi.defaultMenus.LargeSInventory
import tororo1066.tororopluginapi.sInventory.SInventoryItem

class CheckPointListInventory(private val player: Player) : LargeSInventory(Man10CheckPoint.plugin,"§d§lチェックポイントのリスト") {


    override fun renderMenu(): Boolean {
        val items = ArrayList<SInventoryItem>()
        for (data in Man10CheckPoint.checkPoints){
            if (!data.value.checkCheckedPlayer(player.uniqueId) && !player.isOp)continue
            val item = SInventoryItem(data.value.config.getItemStack("item")!!).setCanClick(false).setClickEvent {
                it.whoClicked.closeInventory()
                Bukkit.dispatchCommand(it.whoClicked, "mcp teleport ${data.key}")
            }
            items.add(item)
        }
        setResourceItems(items)
        return true
    }
}