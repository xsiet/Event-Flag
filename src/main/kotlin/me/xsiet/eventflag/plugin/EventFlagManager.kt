package me.xsiet.eventflag.plugin

import org.bukkit.Server
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.event.Event
import org.bukkit.inventory.Inventory
import org.bukkit.scoreboard.Team
import kotlin.reflect.KClass

internal val serverEventKClassList = ArrayList<KClass<out Event>>()
internal val worldEventKClassList = ArrayList<KClass<out Event>>()
internal val blockEventKClassList = ArrayList<KClass<out Event>>()
internal val inventoryEventKClassList = ArrayList<KClass<out Event>>()
internal val entityEventKClassList = ArrayList<KClass<out Event>>()
class EventFlag(private val kClassList: ArrayList<KClass<out Event>>) {
    private val list = ArrayList<KClass<out Event>>()
    private fun classCheck(kClass: KClass<out Event>) {
        if (!kClassList.contains(kClass)) error("")
    }
    fun get(kClass: KClass<out Event>): Boolean {
        classCheck(kClass)
        return !list.contains(kClass)
    }
    fun set(kClass: KClass<out Event>, value: Boolean) {
        classCheck(kClass)
        if (value) list.remove(kClass) else list.add(kClass)
    }
}
private val serverFlagMap = LinkedHashMap<Server, EventFlag>()
private val worldFlagMap = LinkedHashMap<World, EventFlag>()
private val blockFlagMap = LinkedHashMap<Block, EventFlag>()
private val inventoryFlagMap = LinkedHashMap<Inventory, EventFlag>()
private val teamFlagMap = LinkedHashMap<Team, EventFlag>()
private val entityFlagMap = LinkedHashMap<Entity, EventFlag>()
val Server.eventFlag get() = serverFlagMap.getOrPut(this) { EventFlag(serverEventKClassList) }
val World.eventFlag get() = worldFlagMap.getOrPut(this) { EventFlag(worldEventKClassList) }
val Block.eventFlag get() = blockFlagMap.getOrPut(this) { EventFlag(blockEventKClassList) }
val Inventory.eventFlag get() = inventoryFlagMap.getOrPut(this) { EventFlag(inventoryEventKClassList) }
val Team.eventFlag get() = teamFlagMap.getOrPut(this) { EventFlag(entityEventKClassList) }
val Entity.eventFlag get() = entityFlagMap.getOrPut(this) { EventFlag(entityEventKClassList) }