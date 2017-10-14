package simyukkuri.gameobject.yukkuri

import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.resource.ImageList

class YukkuriImpl(stat: YukkuriStat, override val ai: Ai, val imageList: ImageList) : Yukkuri, YukkuriStat by stat