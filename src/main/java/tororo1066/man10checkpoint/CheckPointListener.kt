package tororo1066.man10checkpoint

import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType
import tororo1066.tororopluginapi.sEvent.SEventInterface
import tororo1066.tororopluginapi.sItem.SItem

class CheckPointListener : SEventInterface<PlayerInteractEvent>(Man10CheckPoint.plugin,PlayerInteractEvent::class.java) {

    override fun executeEvent(e: PlayerInteractEvent) {
        if (e.hand != EquipmentSlot.HAND)return
        if (!e.hasItem())return
        val item = SItem(e.item!!)
        val cp = item.getCustomData(Man10CheckPoint.plugin,"AccessPoint", PersistentDataType.STRING)?:return
        val cPData = Man10CheckPoint.checkPoints[cp]?:return
        e.isCancelled = true
        if (cPData.addCheckedPlayer(e.player.uniqueId)){
            e.player.sendMessage(Man10CheckPoint.prefix + "§aあなたは§d${cPData.name}§aにアクセスできるようになりました！")
            e.item!!.amount -= 1
        } else {
            e.player.sendMessage(Man10CheckPoint.prefix + "§4すでにあなたは§d${cPData.name}§4にアクセスできるようになっています")
        }
    }
}