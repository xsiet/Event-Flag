package me.xsiet.eventflag.plugin

import org.bukkit.Server
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.inventory.Inventory

val serverEventClassNameList = ArrayList<String>()
val worldEventClassNameList = ArrayList<String>()
val blockEventClassNameList = ArrayList<String>()
val inventoryEventClassNameList = ArrayList<String>()
val entityEventClassNameList = ArrayList<String>()
private val serverDisallowedEventClassNameList = ArrayList<String>()
private val worldDisallowedEventClassNameListMap = LinkedHashMap<World, ArrayList<String>>()
private val blockDisallowedEventClassNameListMap = LinkedHashMap<Block, ArrayList<String>>()
private val inventoryDisallowedEventClassNameListMap = LinkedHashMap<Inventory, ArrayList<String>>()
private val entityDisallowedEventClassNameListMap = LinkedHashMap<Entity, ArrayList<String>>()
private fun getDisallowedEventNameList(target: Any): ArrayList<String> {
    return when (target) {
        is Server -> serverDisallowedEventClassNameList
        is World -> worldDisallowedEventClassNameListMap.getOrPut(target) { ArrayList() }
        is Block -> blockDisallowedEventClassNameListMap.getOrPut(target) { ArrayList() }
        is Inventory -> inventoryDisallowedEventClassNameListMap.getOrPut(target) { ArrayList() }
        is Entity -> entityDisallowedEventClassNameListMap.getOrPut(target) { ArrayList() }
        else -> ArrayList()
    }
}
private fun getEventFlag(target: Any, className: String) =
    !getDisallowedEventNameList(target).contains(className)
private fun setEventFlag(target: Any, className: String, value: Boolean) =
    getDisallowedEventNameList(target).apply { if (value) remove(className) else add(className) }
fun Server.getEventFlag(className: String) =
    getEventFlag(this, className)
fun Server.setEventFlag(className: String, value: Boolean) =
    setEventFlag(this, className, value)
fun Server.resetAllEventFlags() =
    serverDisallowedEventClassNameList.clear()
fun World.getEventFlag(className: String) =
    getEventFlag(this, className)
fun World.setEventFlag(className: String, value: Boolean) =
    setEventFlag(this, className, value)
fun World.resetAllEventFlags() =
    worldDisallowedEventClassNameListMap.remove(this)
fun Block.getEventFlag(className: String) =
    getEventFlag(this, className)
fun Block.setEventFlag(className: String, value: Boolean) =
    setEventFlag(this, className, value)
fun Block.resetAllFlagValues() =
    blockDisallowedEventClassNameListMap.remove(this)
fun Inventory.getEventFlag(className: String) =
    getEventFlag(this, className)
fun Inventory.setEventFlag(className: String, value: Boolean) =
    setEventFlag(this, className, value)
fun Inventory.resetAllFlagValues() =
    inventoryDisallowedEventClassNameListMap.remove(this)
fun Entity.getEventFlag(className: String) =
    getEventFlag(this, className)
fun Entity.setEventFlag(className: String, value: Boolean) =
    setEventFlag(this, className, value)
fun Entity.resetAllFlagValues() =
    entityDisallowedEventClassNameListMap.remove(this)