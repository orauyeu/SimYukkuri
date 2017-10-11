package simyukkuri.gameobject.yukkuri.factories

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** ゆっくりを生成するクラスのインターフェース */
interface YukkuriFactory {
    companion object {
        /** 両親からその子供のゆっくりを作成する. */
        fun createYukkuriFromParents(father: YukkuriStat, mother: YukkuriStat): YukkuriStat
    }
}