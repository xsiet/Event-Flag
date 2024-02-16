package me.xsiet.eventflag.plugin

import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockEvent
import org.bukkit.event.block.BlockExpEvent
import org.bukkit.event.entity.EntityEvent
import org.bukkit.event.hanging.HangingEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.vehicle.VehicleEvent
import org.bukkit.event.weather.WeatherEvent
import org.bukkit.event.world.WorldEvent
import org.bukkit.plugin.EventExecutor
import org.reflections.Reflections

@Suppress("Unchecked_cast")
class EventFlagEventListener(plugin: EventFlagPlugin): Listener {
    private val server = plugin.server
    init {
        val reflections = Reflections(
            "org.bukkit.event",
            "org.spigotmc.event",
            "com.destroystokyo.paper.event",
            "io.papermc.paper.event"
        )
        val cancellableClasses =
            reflections.getSubTypesOf(Cancellable::class.java)
        fun getClasses(clazz: Class<*>) = reflections.getSubTypesOf(clazz).filter {
            cancellableClasses.contains(it) && it.declaredFields.any { field ->
                field.type.name.endsWith("HandlerList")
            }
        }
        val serverEventClassList = ArrayList<Class<*>>().apply {
            addAll(getClasses(Event::class.java))
            forEach { serverEventClassNameList.add(it.name) }
        }
        ArrayList<Class<*>>().apply {
            addAll(ArrayList<Class<*>>().apply {
                addAll(ArrayList<Class<*>>().apply {
                    addAll(getClasses(InventoryEvent::class.java))
                    forEach { inventoryEventClassNameList.add(it.name) }
                })
                arrayListOf(
                    EntityEvent::class.java,
                    HangingEvent::class.java,
                    PlayerEvent::class.java,
                    VehicleEvent::class.java
                ).forEach { addAll(getClasses(it)) }
                forEach { entityEventClassNameList.add(it.name) }
            })
            addAll(ArrayList<Class<*>>().apply {
                arrayListOf(
                    BlockEvent::class.java,
                    BlockExpEvent::class.java
                ).forEach { addAll(getClasses(it)) }
                forEach { blockEventClassNameList.add(it.name) }
            })
            arrayListOf(
                WeatherEvent::class.java,
                WorldEvent::class.java
            ).forEach { addAll(getClasses(it)) }
            forEach { worldEventClassNameList.add(it.name) }
        }
        serverEventClassList.forEach {
            val executor = EventExecutor { _, event -> onEvent(event, it.name) }
            server.pluginManager.registerEvent(it as Class<out Event>, this, EventPriority.LOWEST, executor, plugin, true)
        }
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