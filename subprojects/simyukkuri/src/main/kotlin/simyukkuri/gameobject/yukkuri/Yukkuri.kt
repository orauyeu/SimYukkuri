package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.resource.Images

interface Yukkuri : YukkuriStat {
    val ai: Ai
    val images: Images
}