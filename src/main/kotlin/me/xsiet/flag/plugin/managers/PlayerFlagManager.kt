package me.xsiet.flag.plugin.managers

import me.xsiet.flag.plugin.enums.PlayerFlag
import org.bukkit.entity.Player

private val map = LinkedHashMap<Player, ArrayList<PlayerFlag>>()
fun Player.getFlagValue(flag: PlayerFlag) = map[this]?.contains(flag) != true
fun Player.setFlagValue(flag: PlayerFlag, value: Boolean) {
    if (value) {
        val list = map[this]
        list?.remove(flag)
        if (list?.isEmpty() == true) map.remove(this)
    }
    else {
        val list = map[this]
        if (list == null) map[this] = ArrayList<PlayerFlag>().apply { add(flag) }
        else list.add(flag)
    }
}