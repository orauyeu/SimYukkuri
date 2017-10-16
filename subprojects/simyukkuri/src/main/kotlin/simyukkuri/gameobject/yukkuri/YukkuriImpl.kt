package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.resource.ImageList

class YukkuriImpl(stats: YukkuriStats, override val ai: Ai, override val imageList: ImageList) : Yukkuri, YukkuriStats by stats