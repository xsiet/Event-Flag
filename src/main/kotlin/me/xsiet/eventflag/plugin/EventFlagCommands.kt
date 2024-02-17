package me.xsiet.eventflag.plugin

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Entity

fun registerEventFlagCommands(server: Server) = commandAPICommand("event-flag") {
    withRequirement {
        it is ConsoleCommandSender || it.isOp
    }
    fun CommandAPICommand.eventFlagCommand(subcommandName: String) {
        val classTextList = ArrayList<String>().apply {
            when (subcommandName) {
                "server" -> serverEventClassNameList
                "world" -> worldEventClassNameList
                "block" -> blockEventClassNameList
                "entity" -> entityEventClassNameList
                else -> ArrayList()
            }.forEach {
                val eventName = it.split(".").last()
                add("${eventName}-${it.replace(".${eventName}", "")}")
            }
        }
        stringArgument("classText") {
            replaceSuggestions(ArgumentSuggestions.strings {
                classTextList.toTypedArray()
            })
        }
        booleanArgument("value")
        anyExecutor { sender, arguments ->
            val classText = arguments["classText"] as String
            val resultMessage: String
            if (classTextList.contains(classText)) {
                val classTextSplit = classText.split("-")
                val className = "${classTextSplit[1]}.${classTextSplit[0]}"
                sender.sendMessage(className)
                val value = arguments["value"] as Boolean
                val target = arguments[subcommandName]
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
    subcommand("server") {
        eventFlagCommand(name)
    }
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