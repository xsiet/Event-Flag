package me.xsiet.eventflag.plugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

class EventFlagPlugin: JavaPlugin() {
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
    }
    override fun onEnable() {
        EventFlagEventListener(this)
        registerEventFlagCommands(server)
    }
}