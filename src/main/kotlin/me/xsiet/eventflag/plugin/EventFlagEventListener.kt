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
import java.lang.reflect.Modifier
import java.util.stream.Collectors
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal class EventFlagEventListener(plugin: EventFlagPlugin): Listener {
    private val server = plugin.server
    private val executor = EventExecutor { _, event ->
        if (event is Cancellable) event.apply {
            fun cancel() { isCancelled = true }
            val kClass = javaClass.kotlin as KClass<out Event>
            try {
                if (!server.eventFlag.get(kClass)) cancel()
                else {
                    fun checkWorldFlag(world: World): Boolean {
                        if (!world.eventFlag.get(kClass)) {
                            cancel()
                            return true
                        }
                        else return false
                    }
                    fun checkEntityFlag(entity: Entity): Boolean {
                        if (!checkWorldFlag(entity.world) && !entity.eventFlag.get(kClass)) {
                            cancel()
                            return true
                        }
                        else return false
                    }
                    when (this) {
                        is BlockEvent -> if (!checkWorldFlag(block.world) && !block.eventFlag.get(kClass)) cancel()
                        is EntityEvent -> checkEntityFlag(entity)
                        is HangingEvent -> checkEntityFlag(entity)
                        is InventoryEvent -> if (!checkEntityFlag(view.player) && !inventory.eventFlag.get(kClass)) cancel()
                        is PlayerEvent -> checkEntityFlag(player)
                        is VehicleEvent -> checkEntityFlag(vehicle)
                        is WeatherEvent -> checkWorldFlag(world)
                        is WorldEvent -> checkWorldFlag(world)
                    }
                }
            }
            catch (_: Exception) {}
        }
    }
    init {
        val reflections = Reflections(
            "org.bukkit.event",
            "org.spigotmc.event",
            "com.destroystokyo.paper.event",
            "io.papermc.paper.event"
        )
        val cancellableClasses = reflections.getSubTypesOf(Cancellable::class.java).stream().filter {
            !Modifier.isAbstract(it.modifiers) && !it.annotations.any {
                annotation -> annotation.toString().contains("@java.lang.Deprecated")
            }
        }.map { it.kotlin as KClass<out Event> }.collect(Collectors.toSet())
        fun ArrayList<KClass<out Event>>.addClasses(kClass: KClass<out Event>) {
            addAll(reflections.getSubTypesOf(kClass.java).stream().filter {
                cancellableClasses.contains(it.kotlin)
            }.map { it.kotlin }.collect(Collectors.toSet()))
        }
        worldEventKClassList.addAll(ArrayList<KClass<out Event>>().apply {
            addClasses(WeatherEvent::class)
            addClasses(WorldEvent::class)
            addAll(ArrayList<KClass<out Event>>().apply {
                addClasses(BlockEvent::class)
                blockEventKClassList.addAll(this)
            })
            addAll(ArrayList<KClass<out Event>>().apply {
                addClasses(InventoryEvent::class)
                inventoryEventKClassList.addAll(this)
                addClasses(EntityEvent::class)
                addClasses(HangingEvent::class)
                addClasses(PlayerEvent::class)
                addClasses(VehicleEvent::class)
                entityEventKClassList.addAll(this)
            })
        })
        cancellableClasses.forEach {
            serverEventKClassList.add(it)
            server.pluginManager.registerEvent(it.java, this, EventPriority.LOWEST, executor, plugin, true)
        }
    }
}