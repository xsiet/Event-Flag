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
import org.reflections.Reflections

@Suppress("UNCHECKED_CAST")
class EventFlagEventListener(plugin: EventFlagPlugin): Listener {
    private val server = plugin.server
    init {
        val reflections = Reflections(
            "org.bukkit.event",
            "org.spigotmc.event",
            "com.destroystokyo.paper.event",
            "io.papermc.paper.event"
        )
        fun getClasses(clazz: Class<*>) = reflections.getSubTypesOf(clazz).filter {
            it.declaredFields.any { field ->
                field.type.name.endsWith("HandlerList")
            }
        }
        ArrayList<String>().apply {
            addAll(ArrayList<String>().apply {
                getClasses(InventoryEvent::class.java).forEach {
                    add(it.name)
                }
                inventoryEventClassNameList.addAll(this)
                arrayListOf(
                    EntityEvent::class.java,
                    HangingEvent::class.java,
                    PlayerEvent::class.java,
                    VehicleEvent::class.java
                ).forEach {
                    getClasses(it).forEach { clazz ->
                        add(clazz.name)
                    }
                }
                entityEventClassNameList.addAll(this)
            })
            addAll(ArrayList<String>().apply {
                getClasses(BlockEvent::class.java).forEach { clazz ->
                    add(clazz.name)
                }
                blockEventClassNameList.addAll(this)
            })
            arrayListOf(
                WeatherEvent::class.java,
                WorldEvent::class.java
            ).forEach {
                getClasses(it).forEach { clazz ->
                    add(clazz.name)
                }
            }
            worldEventClassNameList.addAll(this)
        }
        getClasses(Event::class.java).forEach {
            val className = it.name.apply {
                serverEventClassNameList.add(this)
            }
            server.pluginManager.registerEvent(
                it as Class<out Event>,
                this,
                EventPriority.LOWEST,
                { _, event ->
                    onEvent(event, className)
                },
                plugin,
                true
            )
        }
    }
    private fun onEvent(event: Event, className: String) = event.apply {
        if (this is Cancellable) {
            fun cancel() {
                isCancelled = true
            }
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
                    is BlockEvent -> {
                        if (!checkWorldFlag(block.world) && !block.getEventFlag(className)) cancel()
                    }
                    is EntityEvent -> checkEntityFlag(entity)
                    is HangingEvent -> checkEntityFlag(entity)
                    is InventoryEvent -> {
                        if (!checkEntityFlag(view.player) && !inventory.getEventFlag(className)) cancel()
                    }
                    is PlayerEvent -> checkEntityFlag(player)
                    is VehicleEvent -> checkEntityFlag(vehicle)
                    is WeatherEvent -> checkWorldFlag(world)
                    is WorldEvent -> checkWorldFlag(world)
                }
            }
        }
    }
}