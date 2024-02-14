package me.xsiet.flag.plugin.events

import me.xsiet.flag.plugin.enums.WorldFlag
import me.xsiet.flag.plugin.managers.getFlagValue
import org.bukkit.World
import org.bukkit.event.Cancellable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldEvent

object WorldEventListener: Listener {
    private fun Cancellable.flag(world: World, flag: WorldFlag) { if (!world.getFlagValue(flag)) isCancelled = true }
    //@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
}