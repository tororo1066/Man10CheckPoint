package tororo1066.man10checkpoint

import org.bukkit.command.CommandSender
import tororo1066.man10checkpoint.commands.CreateAccessItem
import tororo1066.man10checkpoint.commands.CreateCheckPoint
import tororo1066.man10checkpoint.commands.SetAccessCheckPoint
import tororo1066.man10checkpoint.commands.TeleportCheckPoint
import tororo1066.tororopluginapi.sCommand.*
import java.util.function.Consumer

class CheckPointCommand : SCommand("mcp",Man10CheckPoint.prefix) {

    init {
        setCommandNoFoundEvent { showHelp(it.sender) }
        registerReportCommand(Man10CheckPoint.plugin,"mcp.user","mcp.op")

        addCommand(SCommandObject().addArg(SCommandArg().addAllowString("help")).setExecutor(Consumer<SCommandData> { showHelp(it.sender) }))

        addCommand(SCommandObject().addNeedPermission("mcp.user").addArg(
                SCommandArg().addAllowString("teleport")).addArg(
                SCommandArg().addAllowType(SCommandArgType.STRING).addAlias("チェックポイント名").addAlias(Man10CheckPoint.checkPoints.map { it.key }.toTypedArray())).setExecutor(TeleportCheckPoint()))

        addCommand(SCommandObject().addNeedPermission("mcp.op").addArg(
                SCommandArg().addAllowString("create")).addArg(
                SCommandArg().addAllowType(SCommandArgType.STRING).addAlias("内部名")).addArg(
                SCommandArg().addAllowType(SCommandArgType.STRING).addAlias("名前")).setExecutor(CreateCheckPoint()))

        addCommand(SCommandObject().addNeedPermission("mcp.op").addArg(
                SCommandArg().addAllowString("setaccess")).addArg(
                SCommandArg().addAllowType(SCommandArgType.ONLINE_PLAYER)).addArg(
                SCommandArg().addAllowType(SCommandArgType.STRING).addAlias("チェックポイント名")).addArg(
                SCommandArg().addAllowType(SCommandArgType.BOOLEAN)).setExecutor(SetAccessCheckPoint())
        )

        addCommand(SCommandObject().addNeedPermission("mcp.op").addArg(
                SCommandArg().addAllowString("createitem")).addArg(
                SCommandArg().addAlias("チェックポイント名")).addArg(
                SCommandArg().addAlias("material")).addArg(
                SCommandArg().addAlias("アイテム名")).addArg(
                SCommandArg().addAlias("ロール")).noLimit(true).setExecutor(CreateAccessItem()))

        addCommand(SCommandObject().addNeedPermission("mcp.op").addArg(
                SCommandArg().addAllowString("reload")
        ).setExecutor(Consumer<SCommandData> {
            Man10CheckPoint.reload()
            it.sender.sendMessage(Man10CheckPoint.prefix + "§aリロードしました")
        }))
    }

    private fun showHelp(sender: CommandSender){
        sender.sendMessage("§a===============§e§lMan10CheckPoint§a===============")
        if (sender.hasPermission("mcp.user")){
            sender.sendMessage("§b/mcp teleport <チェックポイント名> §dチェックポイントにテレポートします")
            sender.sendMessage("§b/mcp report <件名> <本文> §dこのプラグインについてレポートします")
        }
        if (sender.hasPermission("mcp.op")){
            sender.sendMessage("§b/mcp create <内部名> <名前> §d立っている場所にチェックポイントを作ります")
            sender.sendMessage("§b/mcp setaccess <プレイヤー名> <チェックポイント名> <true/false> §dチェックポイントにアクセス可能かどうか指定します")
            sender.sendMessage("§b/mcp createitem <チェックポイント名> <material> <アイテム名> <ロール...> §dチェックポイントにアクセスできるようになるアイテムを作成します")
            sender.sendMessage("§b/mcp reload プラグインをリロードします")
        }
        sender.sendMessage("§a===============§e§lMan10CheckPoint§a===============")
    }
}