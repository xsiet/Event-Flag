package me.xsiet.eventflag.plugin

import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockEvent
import org.bukkit.event.entity.EntityEvent
import org.bukkit.event.hanging.HangingEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.vehicle.VehicleEvent
import org.bukkit.event.weather.WeatherEvent
import org.bukkit.event.world.WorldEvent
import org.bukkit.plugin.EventExecutor
import org.reflections.Reflections

class EventFlagEventListener(plugin: EventFlagPlugin): Listener {
    private val server = plugin.server
    init {
        val classList = ArrayList<Class<*>>()
        val classNameList = ArrayList<String>()
        val reflections = Reflections(
            "org.bukkit.event",
            "org.spigotmc.event",
            "com.destroystokyo.paper.event",
            "io.papermc.paper.event"
        )
        classList.addAll(reflections.getSubTypesOf(Cancellable::class.java))
        reflections.getSubTypesOf(Event::class.java).filter {
            classList.contains(it) && it.declaredFields.any { field -> field.type.name.endsWith("HandlerList") }
        }.toSet().forEach { eventClass ->
            val className = eventClass.name.apply { classNameList.add(this) }
            val executor = EventExecutor { _, event -> onEvent(event, className) }
            server.pluginManager.registerEvent(eventClass, this, EventPriority.LOWEST, executor, plugin, true)
        }
        registerEventFlagCommands(classNameList, server)
    }
    private fun onEvent(event: Event, className: String) = (event as Cancellable).apply {
        fun cancel() { isCancelled = true }
        if (!server.getEventFlag(className)) cancel()
        else {
            fun checkWorldFlag(world: World): Boolean {
                if (!world.getEventFlag(className)) {
                    cancel()
                    return true
                }
                else return false
            }
            fun checkEntityFlag(entity: Entity): Boolean {
                if (!checkWorldFlag(entity.world) && !entity.getEventFlag(className)) {
                    cancel()
                    return true
                }
                else return false
            }
            when (this) {
                is BlockEvent -> if (!checkWorldFlag(block.world) && !block.getEventFlag(className)) cancel()
                is EntityEvent -> checkEntityFlag(entity)
                is HangingEvent -> checkEntityFlag(entity)
                is InventoryEvent -> if (!checkEntityFlag(view.player) && !inventory.getEventFlag(className)) cancel()
                is PlayerEvent -> checkEntityFlag(player)
                is VehicleEvent -> checkEntityFlag(vehicle)
                is WeatherEvent -> checkWorldFlag(world)
                is WorldEvent -> checkWorldFlag(world)
            }
        }
    }
}