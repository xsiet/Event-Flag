package me.xsiet.eventflag.plugin

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Entity

fun registerEventFlagCommands(
    classNameList: ArrayList<String>,
    server: Server
) = commandAPICommand("event-flag") {
    withRequirement { sender -> sender is ConsoleCommandSender || sender.isOp }
    fun CommandAPICommand.eventFlagCommand(subcommandName: String) {
        stringArgument("className") {
            replaceSuggestions(ArgumentSuggestions.strings { classNameList.toTypedArray() })
        }
        booleanArgument("value")
        anyExecutor { sender, arguments ->
            val resultMessage: String
            val className = arguments["className"] as String
            if (classNameList.contains(className)) {
                val target = arguments[subcommandName]
                val value = arguments["value"] as Boolean
                when (subcommandName) {
                    "server" -> server.setEventFlag(className, value)
                    "world" -> (target as World).setEventFlag(className, value)
                    "block" -> (target as Location).block.setEventFlag(className, value)
                    "entity" -> (target as Entity).setEventFlag(className, value)
                }
                resultMessage = "성공"
            }
            else resultMessage = "에러"
            sender.sendMessage(resultMessage)
        }
    }
    subcommand("server") { eventFlagCommand(name) }
    subcommand("world") {
        worldArgument(name)
        eventFlagCommand(name)
    }
    subcommand("block") {
        locationArgument(name)
        eventFlagCommand(name)
    }
    subcommand("entity") {
        entitySelectorArgumentOneEntity(name)
        eventFlagCommand(name)
    }
}