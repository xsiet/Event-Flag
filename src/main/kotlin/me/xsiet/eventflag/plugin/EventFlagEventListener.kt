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
import java.util.stream.Collectors
import kotlin.collections.ArrayList

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
        val cancellableClasses = reflections.getSubTypesOf(Cancellable::class.java).stream().collect(Collectors.toSet())
        fun Class<*>.filter() = cancellableClasses.contains(this)
        ArrayList<String>().apply {
            addAll(ArrayList<String>().apply {
                reflections.getSubTypesOf(InventoryEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                inventoryEventClassNameList.addAll(this)
                reflections.getSubTypesOf(EntityEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                reflections.getSubTypesOf(HangingEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                reflections.getSubTypesOf(PlayerEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                reflections.getSubTypesOf(VehicleEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                entityEventClassNameList.addAll(this)
            })
            addAll(ArrayList<String>().apply {
                reflections.getSubTypesOf(BlockEvent::class.java).stream().filter {
                    it.filter()
                }.collect(Collectors.toSet()).forEach {
                    add(it.name)
                }
                blockEventClassNameList.addAll(this)
            })
            reflections.getSubTypesOf(WeatherEvent::class.java).stream().filter {
                it.filter()
            }.collect(Collectors.toSet()).forEach {
                add(it.name)
            }
            reflections.getSubTypesOf(WorldEvent::class.java).stream().filter {
                it.filter()
            }.collect(Collectors.toSet()).forEach {
                add(it.name)
            }
            worldEventClassNameList.addAll(this)
        }
        cancellableClasses.forEach {
            var className = it.name.apply {
                serverEventClassNameList.add(this)
            }
            try {
                server.pluginManager.registerEvent(
                    it as Class<out Event>,
                    this,
                    EventPriority.LOWEST,
                    { _, event ->
                        if (event is Cancellable) event.apply {
                            className = javaClass.name
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
                    },
                    plugin,
                    true
                )
            }
            catch (_: Exception) {
                arrayListOf(
                    serverEventClassNameList,
                    worldEventClassNameList,
                    blockEventClassNameList,
                    inventoryEventClassNameList,
                    entityEventClassNameList
                ).forEach { list ->
                    list.remove(className)
                }
            }
        }
    }
}