package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.resource.Images

class YukkuriImpl(stats: YukkuriStats, override val ai: Ai, override val images: Images) : Yukkuri, YukkuriStats by stats