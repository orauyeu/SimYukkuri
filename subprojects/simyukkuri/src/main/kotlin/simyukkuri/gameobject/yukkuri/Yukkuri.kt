package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.resource.ImageList

interface Yukkuri : YukkuriStats {
    val ai: Ai
    val imageList: ImageList
}