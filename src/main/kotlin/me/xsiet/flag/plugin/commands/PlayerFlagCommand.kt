package me.xsiet.flag.plugin.commands

import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import me.xsiet.flag.plugin.enums.PlayerFlag
import me.xsiet.flag.plugin.enums.WorldFlag
import me.xsiet.flag.plugin.managers.setFlagValue
import org.bukkit.Server
import org.bukkit.command.ConsoleCommandSender

object PlayerFlagCommand {
    fun register(server: Server) {
        commandAPICommand("flag") {
            withRequirement { sender -> sender is ConsoleCommandSender || sender.isOp }
            subcommand("player") {
                val flagEntries = PlayerFlag.entries
                stringArgument("targetName") {
                    replaceSuggestions(ArgumentSuggestions.strings { server.onlinePlayers.map { it.name }.toTypedArray() })
                }
                stringArgument("flagName") {
                    replaceSuggestions(ArgumentSuggestions.strings { flagEntries.map { it.flagName }.toTypedArray() })
                }
                booleanArgument("value")
                anyExecutor { sender, arguments ->
                    val target = server.getPlayer(arguments["targetName"] as String)
                    val flag = flagEntries.find { it.flagName == arguments["flagName"] as String }
                    if (target == null) sender.sendMessage("A")
                    else if (flag == null) sender.sendMessage("B")
                    else {
                        target.setFlagValue(flag, arguments["value"] as Boolean)
                    }
                    sender.sendMessage("Ok.")
                }
            }
            subcommand("world") {
                worldArgument("target")
                stringArgument("flagName") {
                    replaceSuggestions(ArgumentSuggestions.strings { WorldFlag.entries.map { it.flagName }.toTypedArray() })
                }
                booleanArgument("value")
                anyExecutor { sender, arguments -> }
            }
        }
    }
}