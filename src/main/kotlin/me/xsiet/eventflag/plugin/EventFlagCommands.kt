package me.xsiet.eventflag.plugin

import com.destroystokyo.paper.network.StandardPaperServerListPingEventImpl
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.Component.text
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Entity
import org.bukkit.event.Event
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal fun registerEventFlagCommands(server: Server) = commandAPICommand("event-flag") {
    withRequirement { it is ConsoleCommandSender || it.isOp }
    fun CommandAPICommand.eventFlagCommand(subcommandName: String) {
        val packageNameMap = LinkedHashMap<String, String>().apply {
            when (subcommandName) {
                "server" -> serverEventKClassList
                "world" -> worldEventKClassList
                "block" -> blockEventKClassList
                "team", "entities" -> entityEventKClassList
                else -> ArrayList()
            }.forEach { set(it.simpleName!!, it.java.packageName) }
        }
        val eventNameArray = packageNameMap.keys.toTypedArray()
        stringArgument("eventName", true) {
            replaceSuggestions(ArgumentSuggestions.strings { eventNameArray })
        }
        booleanArgument("value", true)
        anyExecutor { sender, arguments ->
            val eventArgument = arguments["eventName"]
            var resultMessage = text("")
            if (eventArgument == null) {

            }
            else {
                val eventName = arguments["eventName"] as String
                if (eventNameArray.contains(eventName)) {
                    val className = "${packageNameMap[eventName]}.${eventName}"
                    val kClass = Class.forName(className).kotlin as KClass<out Event>
                    val valueArgument = arguments["value"]
                    if (valueArgument == null) when (subcommandName) {
                        "server" -> {
                            resultMessage = text("")
                        }
                        "world" -> {
                            resultMessage = text("")
                        }
                        "block" -> {
                            resultMessage = text("")
                        }
                        "team" -> {
                            resultMessage = text("")
                        }
                        "entities" -> {
                            resultMessage = text("")
                        }
                    }
                    else {
                        val value = arguments["value"] as Boolean
                        val target = arguments[subcommandName]
                        when (subcommandName) {
                            "server" -> {
                                server.eventFlag.apply {
                                    if (get(kClass) == value) resultMessage = text("")
                                    else {
                                        set(kClass, value)
                                        resultMessage = text("")
                                    }
                                }
                            }
                            "world" -> {
                                (target as World).eventFlag.set(kClass, value)
                                resultMessage = text("")
                            }
                            "block" -> {
                                (target as Location).block.eventFlag.set(kClass, value)
                                resultMessage = text("")
                            }
                            "entity" -> {
                                (target as Collection<*>).forEach {
                                    (it as Entity).eventFlag.set(kClass, value)
                                    resultMessage = text("")
                                }
                            }
                        }
                    }
                }
            }
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
    subcommand("team") {
        teamArgument(name)
        eventFlagCommand(name)
    }
    subcommand("entities") {
        entitySelectorArgumentManyEntities(name)
        eventFlagCommand(name)
    }
}