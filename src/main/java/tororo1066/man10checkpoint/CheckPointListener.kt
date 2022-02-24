package tororo1066.man10checkpoint

import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType
import tororo1066.tororopluginapi.sEvent.SEvent
import tororo1066.tororopluginapi.sItem.SItem

class CheckPointListener {

    init {
        SEvent(Man10CheckPoint.plugin).register(PlayerInteractEvent::class.java) { e ->
            if (e.hand != EquipmentSlot.HAND)return@register
            if (!e.hasItem())return@register
            val item = SItem(e.item!!)
            val cp = item.getCustomData(Man10CheckPoint.plugin,"AccessPoint", PersistentDataType.STRING)?:return@register
            val cPData = Man10CheckPoint.checkPoints[cp]?:return@register
            e.isCancelled = true
            if (cPData.addCheckedPlayer(e.player.uniqueId)){
                e.player.sendMessage(Man10CheckPoint.prefix + "§aあなたは§d${cPData.name}§aにアクセスできるようになりました！")
                e.item!!.amount -= 1
            } else {
                e.player.sendMessage(Man10CheckPoint.prefix + "§4すでにあなたは§d${cPData.name}§4にアクセスできるようになっています")
            }


        }
    }
}