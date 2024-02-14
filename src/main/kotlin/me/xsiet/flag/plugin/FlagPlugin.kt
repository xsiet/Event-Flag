package me.xsiet.flag.plugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import me.xsiet.flag.plugin.commands.PlayerFlagCommand
import me.xsiet.flag.plugin.events.PlayerEventListener
import org.bukkit.plugin.java.JavaPlugin

class FlagPlugin: JavaPlugin() {
    override fun onLoad() { CommandAPI.onLoad(CommandAPIBukkitConfig(this)) }
    override fun onEnable() {
        server.pluginManager.registerEvents(PlayerEventListener, this)
        PlayerFlagCommand.register(server)
    }
    override fun onDisable() {}
}