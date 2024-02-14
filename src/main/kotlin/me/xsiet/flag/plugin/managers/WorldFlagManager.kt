package me.xsiet.flag.plugin.managers

import me.xsiet.flag.plugin.enums.WorldFlag
import org.bukkit.World

private val map = LinkedHashMap<World, ArrayList<WorldFlag>>()
fun World.getFlagValue(flag: WorldFlag) = map[this]?.contains(flag) != true
fun World.setFlagValue(flag: WorldFlag, value: Boolean) {
    if (value) {
        val list = map[this]
        list?.remove(flag)
        if (list?.isEmpty() == true) map.remove(this)
    }
    else {
        val list = map[this]
        if (list == null) map[this] = ArrayList<WorldFlag>().apply { add(flag) }
        else list.add(flag)
    }
}