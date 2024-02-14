package me.xsiet.template.plugin

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

class TemplatePlugin: JavaPlugin() {
    override fun onLoad() { CommandAPI.onLoad(CommandAPIBukkitConfig(this)) }
    override fun onEnable() {}
    override fun onDisable() {}
}