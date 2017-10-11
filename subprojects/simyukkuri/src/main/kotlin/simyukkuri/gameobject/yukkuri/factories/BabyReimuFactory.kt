package simyukkuri.gameobject.yukkuri.factories

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** 赤れいむを生成するクラス */
object BabyReimuFactory : AbstractYukkuriFactory() {
    override fun createYukkuriFromParents(father: YukkuriStat, mother: YukkuriStat)
}