package org.cneko.justarod.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.cneko.ctlib.common.network.HttpGet.HttpGetObject
import org.cneko.justarod.Justarod.MODID
import org.cneko.justarod.item.NetWorkingRodItem

class NetWorkingRodData {
    companion object{
        val URL = "https://api.justarod.cneko.org/v0/get/"
        var SPEED = 1
        var MAX_DAMAGE = 1000

        fun init() {
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val req = HttpGetObject(URL)
                req.let {
                    it.get()
                    val json = it.json
                    MAX_DAMAGE = json.getInt("max_damage")
                    SPEED = json.getInt("speed")
                }
                if (MAX_DAMAGE == 0){
                    MAX_DAMAGE = 1000
                }
                if (SPEED == 0){
                    SPEED = 1
                }
                 Registry.register(Registries.ITEM, Identifier.of(MODID, "networking_rod"),NetWorkingRodItem())
            }
        }
    }
}