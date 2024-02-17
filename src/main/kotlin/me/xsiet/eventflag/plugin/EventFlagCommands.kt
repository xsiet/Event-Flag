package me.xsiet.eventflag.plugin

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Entity

internal fun registerEventFlagCommands(server: Server) = commandAPICommand("event-flag") {
    withRequirement { it is ConsoleCommandSender || it.isOp }
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
            replaceSuggestions(ArgumentSuggestions.strings { classTextList.toTypedArray() })
        }
        booleanArgument("value", true)
        anyExecutor { sender, arguments ->
            val classText = arguments["classText"] as String
            var normalMessage = ""
            var successMessage = ""
            var errorMessage = ""
            if (classTextList.contains(classText)) {
                val classTextSplit = classText.split("-")
                val className = "${classTextSplit[1]}.${classTextSplit[0]}"
                val valueArgument = arguments["value"]
                if (valueArgument == null) when (subcommandName) {
                    "server" -> {
                        normalMessage = "이 서버의 ${classText}값은 "
                    }
                    "world" -> {
                        normalMessage = ""
                    }
                    "block" -> {
                        normalMessage = ""
                    }
                    "entity" -> {
                        normalMessage = ""
                    }
                }
                else {
                    val value = arguments["value"] as Boolean
                    val target = arguments[subcommandName]
                    when (subcommandName) {
                        "server" -> {
                            server.eventFlag.apply {
                                if (get(className) == value) errorMessage = ""
                                else {
                                    set(className, value)
                                    successMessage = ""
                                }
                            }
                        }
                        "world" -> {
                            (target as World).eventFlag.set(className, value)
                            r = ""
                        }
                        "block" -> {
                            (target as Location).block.eventFlag.set(className, value)
                            resultMessage = ""
                        }
                        "entity" -> {
                            (target as Collection<*>).forEach {
                                (it as Entity).eventFlag.set(className, value)
                                resultMessage = ""
                            }
                        }
                    }
                }
            }
            if (normalMessage != "") sender.sendMessage(text(normalMessage, NamedTextColor.YELLOW))
            else {
                if (errorMessage != "") sender.sendMessage(text(errorMessage, NamedTextColor.RED))
                if (successMessage != "") sender.sendMessage(text(successMessage, NamedTextColor.GREEN))
            }
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
        entitySelectorArgumentManyEntities(name)
        eventFlagCommand(name)
    }
}