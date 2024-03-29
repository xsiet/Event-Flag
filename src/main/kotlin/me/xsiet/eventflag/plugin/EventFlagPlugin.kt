package me.xsiet.eventflag.plugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

internal class EventFlagPlugin: JavaPlugin() {
    override fun onLoad() { CommandAPI.onLoad(CommandAPIBukkitConfig(this)) }
    override fun onEnable() {
        server.scheduler.runTaskLater(this, Runnable {
            EventFlagEventListener(this)
            registerEventFlagCommands(server)
        }, 0)
    }
}