package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.resource.Images

class YukkuriImpl(stat: YukkuriStat, override val ai: Ai, override val images: Images) : Yukkuri, YukkuriStat by stat