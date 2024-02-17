package me.xsiet.eventflag.plugin

import org.bukkit.Server
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.inventory.Inventory

internal val serverEventClassNameList = ArrayList<String>()
internal val worldEventClassNameList = ArrayList<String>()
internal val blockEventClassNameList = ArrayList<String>()
internal val inventoryEventClassNameList = ArrayList<String>()
internal val entityEventClassNameList = ArrayList<String>()
class EventFlag(private val classNameList: ArrayList<String>) {
    private val list = ArrayList<String>()
    private fun classNameCheck(className: String) {
        if (!classNameList.contains(className)) error("")
    }
    fun get(className: String): Boolean {
        classNameCheck(className)
        return !list.contains(className)
    }
    fun set(className: String, value: Boolean) {
        classNameCheck(className)
        if (value) list.remove(className) else list.add(className)
    }
}
private var serverEventFlag = EventFlag(serverEventClassNameList)
private val worldEventFlagMap = LinkedHashMap<World, EventFlag>()
private val blockEventFlagMap = LinkedHashMap<Block, EventFlag>()
private val inventoryEventFlagMap = LinkedHashMap<Inventory, EventFlag>()
private val entityEventFlagMap = LinkedHashMap<Entity, EventFlag>()
val Server.eventFlag get() = serverEventFlag
fun Server.resetAllEventFlag() { serverEventFlag = EventFlag(serverEventClassNameList) }
val World.eventFlag get() = worldEventFlagMap.getOrPut(this) { EventFlag(worldEventClassNameList) }
fun World.resetAllEventFlag() = worldEventFlagMap.remove(this)
val Block.eventFlag get() = blockEventFlagMap.getOrPut(this) { EventFlag(blockEventClassNameList) }
fun Block.resetAllEventFlag() = blockEventFlagMap.remove(this)
val Inventory.eventFlag get() = inventoryEventFlagMap.getOrPut(this) { EventFlag(inventoryEventClassNameList) }
fun Inventory.resetAllEventFlag() = inventoryEventFlagMap.remove(this)
val Entity.eventFlag get() = entityEventFlagMap.getOrPut(this) { EventFlag(entityEventClassNameList) }
fun Entity.resetAllEventFlag() = entityEventFlagMap.remove(this)